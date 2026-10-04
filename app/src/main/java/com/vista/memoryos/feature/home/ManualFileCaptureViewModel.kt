package com.vista.memoryos.feature.home

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.usecase.CreateVistaFileUseCase
import com.vista.memoryos.domain.usecase.SaveFileUseCase
import com.vista.memoryos.domain.usecase.UploadVistaFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ManualFileCaptureViewModel @Inject constructor(
    private val supabase: SupabaseClient,
    private val createVistaFileUseCase: CreateVistaFileUseCase,
    private val getFileUseCase: com.vista.memoryos.domain.usecase.GetFileUseCase,
    private val saveFileUseCase: SaveFileUseCase,
    private val uploadVistaFileUseCase: UploadVistaFileUseCase
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
                val userId = getAuthenticatedUserId()

                var completedCount = 0
                var failedCount = 0
                var lastErrorMessage: String? = null

                for (uri in uris) {

                    var vistaFile =
                        createVistaFileUseCase.create(
                            context = context,
                            userId = userId,
                            sourceUri = uri
                        )

                    try {
                        saveFileUseCase(vistaFile)

                        vistaFile =
                            vistaFile.copy(
                                uploadStatus = FileUploadStatus.UPLOADING
                            )

                        saveFileUseCase(vistaFile)

                        val cloudPath =
                            uploadVistaFileUseCase(
                                userId = vistaFile.userId,
                                fileId = vistaFile.fileId,
                                sourceUri = uri,
                                contentType = vistaFile.mimeType
                            )

                        vistaFile =
                            vistaFile.copy(
                                cloudPath = cloudPath,
                                uploadStatus = FileUploadStatus.UPLOADED
                            )

                        saveFileUseCase(vistaFile)

                        completedCount++

                    } catch (e: Exception) {

                        failedCount++

                        lastErrorMessage =
                            getUserFriendlyUploadError(e)

                        vistaFile =
                            vistaFile.copy(
                                uploadStatus = FileUploadStatus.FAILED
                            )

                        saveFileUseCase(vistaFile)
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
                                failed = failedCount,
                                errorMessage = lastErrorMessage
                            )
                    }

                    else -> {
                        _captureState.value =
                            ManualFileCaptureState.Error(
                                lastErrorMessage
                                    ?: "Unable to upload the selected file."
                            )
                    }
                }

            } catch (e: Exception) {
                _captureState.value =
                    ManualFileCaptureState.Error(
                        getUserFriendlyUploadError(e)
                    )
            }
        }
    }

    fun retryUpload(file: VistaFile) {
        viewModelScope.launch {

            _captureState.value =
                ManualFileCaptureState.Retrying(
                    fileId = file.fileId
                )

            try {
                val sourceUriString =
                    file.sourceUri
                        ?: throw IllegalStateException(
                            "Original file reference is unavailable"
                        )

                val sourceUri =
                    Uri.parse(sourceUriString)

                val uploadingFile =
                    file.copy(
                        uploadStatus = FileUploadStatus.UPLOADING
                    )

                saveFileUseCase(uploadingFile)

                val cloudPath =
                    uploadVistaFileUseCase(
                        userId = file.userId,
                        fileId = file.fileId,
                        sourceUri = sourceUri,
                        contentType = file.mimeType
                    )

                val uploadedFile =
                    uploadingFile.copy(
                        cloudPath = cloudPath,
                        uploadStatus = FileUploadStatus.UPLOADED
                    )

                saveFileUseCase(uploadedFile)

                _captureState.value =
                    ManualFileCaptureState.RetrySuccess(
                        fileName = file.displayName
                    )

            } catch (e: Exception) {

                val failedFile =
                    file.copy(
                        uploadStatus = FileUploadStatus.FAILED
                    )

                try {
                    saveFileUseCase(failedFile)
                } catch (_: Exception) {
                }

                _captureState.value =
                    ManualFileCaptureState.Error(
                        getUserFriendlyUploadError(e)
                    )
            }
        }
    }

    private suspend fun getAuthenticatedUserId(): String {

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

        return authenticatedStatus.session.user?.id
            ?: throw IllegalStateException(
                "Authenticated session has no user"
            )
    }

    private fun getUserFriendlyUploadError(
        exception: Throwable
    ): String {

        var current: Throwable? = exception

        while (current != null) {

            if (current is UnknownHostException) {
                return "No internet connection. Please check your connection and tap Retry Upload."
            }

            if (current is IOException) {
                return "Network connection failed. Please check your connection and tap Retry Upload."
            }

            current = current.cause
        }

        val message =
            exception.message
                ?.lowercase()
                .orEmpty()

        if (
            message.contains("unable to resolve host") ||
            message.contains("no address associated with hostname") ||
            message.contains("network is unreachable") ||
            message.contains("failed to connect")
        ) {
            return "No internet connection. Please check your connection and tap Retry Upload."
        }

        if (
            message.contains("403") ||
            message.contains("row-level security") ||
            message.contains("forbidden")
        ) {
            return "VISTA could not access cloud storage. Please try again."
        }

        if (
            message.contains("401") ||
            message.contains("unauthorized")
        ) {
            return "Your session has expired. Please sign in again."
        }

        if (
            message.contains("timeout") ||
            message.contains("timed out")
        ) {
            return "The upload timed out. Please check your connection and tap Retry Upload."
        }

        return "VISTA could not upload this file. Please try again."
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

    data class Retrying(
        val fileId: String
    ) : ManualFileCaptureState

    data class Success(
        val count: Int
    ) : ManualFileCaptureState

    data class RetrySuccess(
        val fileName: String
    ) : ManualFileCaptureState

    data class PartialSuccess(
        val total: Int,
        val successful: Int,
        val failed: Int,
        val errorMessage: String?
    ) : ManualFileCaptureState

    data class Error(
        val message: String
    ) : ManualFileCaptureState
}