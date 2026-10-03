package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.repository.FileInventoryRepository
import javax.inject.Inject

class DeleteFileUseCase @Inject constructor(
    private val repository: FileInventoryRepository
) {

    suspend operator fun invoke(fileId: String) {
        repository.deleteFile(fileId)
    }
}