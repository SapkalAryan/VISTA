package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.repository.FileStorageRepository
import javax.inject.Inject

class DeleteVistaCloudFileUseCase @Inject constructor(
    private val fileStorageRepository: FileStorageRepository
) {

    suspend operator fun invoke(
        cloudPath: String
    ) {
        fileStorageRepository.deleteFile(
            cloudPath = cloudPath
        )
    }
}