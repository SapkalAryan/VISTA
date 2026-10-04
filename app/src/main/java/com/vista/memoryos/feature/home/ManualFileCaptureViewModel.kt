package com.vista.memoryos.feature.home

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vista.memoryos.domain.usecase.CreateVistaFileUseCase
import com.vista.memoryos.domain.usecase.SaveFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManualFileCaptureViewModel @Inject constructor(
    private val supabase: SupabaseClient,
    private val createVistaFileUseCase: CreateVistaFileUseCase,
    private val saveFileUseCase: SaveFileUseCase
) : ViewModel() {

    private val _captureState =
        MutableStateFlow<ManualFileCaptureState>(
            ManualFileCaptureState.Idle
        )

    val captureState: StateFlow<ManualFileCaptureState> = _captureState

    fun captureFiles(
        context: Context,
        uris: List<Uri>
    ) {
        if (uris.isEmpty()) {
            return
        }

        viewModelScope.launch {
            _captureState.value =
                ManualFileCaptureState.Saving(
                    total = uris.size,
                    completed = 0,
                    failed = 0
                )

            try {
                val sessionStatus =
                    supabase.auth.sessionStatus.first { status ->
                        status is SessionStatus.Authenticated ||
                                status is SessionStatus.NotAuthenticated
                    }

                val authenticatedStatus =
                    sessionStatus as? SessionStatus.Authenticated
                        ?: throw IllegalStateException(
                            "No authenticated user"
                        )

                val userId =
                    authenticatedStatus.session.user?.id
                        ?: throw IllegalStateException(
                            "Authenticated session has no user"
                        )

                var completedCount = 0
                var failedCount = 0

                for (uri in uris) {
                    try {
                        val vistaFile =
                            createVistaFileUseCase.create(
                                context = context,
                                userId = userId,
                                sourceUri = uri
                            )

                        saveFileUseCase(vistaFile)

                        completedCount++
                    } catch (_: Exception) {
                        failedCount++
                    }

                    _captureState.value =
                        ManualFileCaptureState.Saving(
                            total = uris.size,
                            completed = completedCount,
                            failed = failedCount
                        )
                }

                when {
                    completedCount == uris.size -> {
                        _captureState.value =
                            ManualFileCaptureState.Success(
                                count = completedCount
                            )
                    }

                    completedCount > 0 -> {
                        _captureState.value =
                            ManualFileCaptureState.PartialSuccess(
                                total = uris.size,
                                successful = completedCount,
                                failed = failedCount
                            )
                    }

                    else -> {
                        _captureState.value =
                            ManualFileCaptureState.Error(
                                "Unable to add the selected file(s)."
                            )
                    }
                }

            } catch (e: Exception) {
                _captureState.value =
                    ManualFileCaptureState.Error(
                        e.message ?: "Unable to add file(s)"
                    )
            }
        }
    }

    fun resetState() {
        _captureState.value =
            ManualFileCaptureState.Idle
    }
}

sealed interface ManualFileCaptureState {

    data object Idle : ManualFileCaptureState

    data class Saving(
        val total: Int,
        val completed: Int,
        val failed: Int
    ) : ManualFileCaptureState

    data class Success(
        val count: Int
    ) : ManualFileCaptureState

    data class PartialSuccess(
        val total: Int,
        val successful: Int,
        val failed: Int
    ) : ManualFileCaptureState

    data class Error(
        val message: String
    ) : ManualFileCaptureState
}