package com.vista.memoryos.domain.usecase

import android.net.Uri
import com.vista.memoryos.domain.repository.FileStorageRepository
import javax.inject.Inject

class UploadVistaFileUseCase @Inject constructor(
    private val fileStorageRepository: FileStorageRepository
) {

    suspend operator fun invoke(
        userId: String,
        fileId: String,
        sourceUri: Uri,
        contentType: String
    ): String {
        return fileStorageRepository.uploadFile(
            userId = userId,
            fileId = fileId,
            sourceUri = sourceUri,
            contentType = contentType
        )
    }
}