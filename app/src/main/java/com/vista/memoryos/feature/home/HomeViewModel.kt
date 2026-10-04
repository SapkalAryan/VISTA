package com.vista.memoryos.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.usecase.DeleteFileUseCase
import com.vista.memoryos.domain.usecase.DeleteVistaCloudFileUseCase
import com.vista.memoryos.domain.usecase.ObserveFilesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val observeFilesUseCase: ObserveFilesUseCase,
    private val deleteFileUseCase: DeleteFileUseCase,
    private val deleteVistaCloudFileUseCase: DeleteVistaCloudFileUseCase
) : ViewModel() {

    private val _files =
        MutableStateFlow<List<VistaFile>>(emptyList())

    val files: StateFlow<List<VistaFile>> =
        _files.asStateFlow()

    private val _isLoading =
        MutableStateFlow(true)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage.asStateFlow()

    private val _deleteState =
        MutableStateFlow<DeleteState>(DeleteState.Idle)

    val deleteState: StateFlow<DeleteState> =
        _deleteState.asStateFlow()

    init {
        observeFiles()
    }

    private fun observeFiles() {
        viewModelScope.launch {
            observeFilesUseCase()
                .collect { inventory ->

                    _files.value = inventory
                    _isLoading.value = false
                }
        }
    }

    fun deleteFile(file: VistaFile) {

        if (_deleteState.value is DeleteState.Deleting) {
            return
        }

        viewModelScope.launch {

            _errorMessage.value = null

            _deleteState.value =
                DeleteState.Deleting(
                    fileId = file.fileId
                )

            try {

                /*
                 * Delete the managed cloud copy first.
                 *
                 * If cloud deletion fails, the local inventory
                 * record is intentionally preserved.
                 */
                file.cloudPath?.let { cloudPath ->

                    deleteVistaCloudFileUseCase(
                        cloudPath
                    )
                }

                /*
                 * Only remove the Room inventory record after
                 * cloud deletion has succeeded.
                 */
                deleteFileUseCase(
                    file.fileId
                )

                _deleteState.value =
                    DeleteState.Success(
                        fileId = file.fileId
                    )

            } catch (e: Exception) {

                _deleteState.value =
                    DeleteState.Failed(
                        fileId = file.fileId
                    )

                _errorMessage.value =
                    getUserFriendlyDeleteError(e)
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetDeleteState() {
        _deleteState.value =
            DeleteState.Idle
    }

    private fun getUserFriendlyDeleteError(
        exception: Throwable
    ): String {

        val message =
            exception.message
                ?.lowercase()
                .orEmpty()

        return when {

            message.contains("unable to resolve host") ||
                    message.contains("no address associated with hostname") ||
                    message.contains("network is unreachable") ||
                    message.contains("failed to connect") ||
                    message.contains("timeout") ||
                    message.contains("timed out") -> {

                "No internet connection. Please check your connection and try again."
            }

            message.contains("403") ||
                    message.contains("forbidden") ||
                    message.contains("row-level security") -> {

                "VISTA could not access the cloud file. Please try again."
            }

            message.contains("401") ||
                    message.contains("unauthorized") -> {

                "Your session has expired. Please sign in again."
            }

            else -> {
                "VISTA could not delete this file. Please try again."
            }
        }
    }
}

sealed interface DeleteState {

    data object Idle : DeleteState

    data class Deleting(
        val fileId: String
    ) : DeleteState

    data class Success(
        val fileId: String
    ) : DeleteState

    data class Failed(
        val fileId: String
    ) : DeleteState
}