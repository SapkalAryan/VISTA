package com.vista.memoryos.domain.usecase

import com.vista.memoryos.data.room.dao.FileDao
import com.vista.memoryos.domain.model.FileUploadStatus
import javax.inject.Inject

class UpdateFileUploadStatusUseCase @Inject constructor(
    private val fileDao: FileDao
) {

    suspend operator fun invoke(
        fileId: String,
        status: FileUploadStatus,
        updatedAt: Long = System.currentTimeMillis()
    ) {
        fileDao.updateUploadStatus(
            fileId = fileId,
            uploadStatus = status,
            updatedAt = updatedAt
        )
    }
}