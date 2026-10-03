package com.vista.memoryos.domain.usecase

import com.vista.memoryos.data.room.dao.FileDao
import com.vista.memoryos.domain.model.FileProcessingStatus
import javax.inject.Inject

class UpdateFileProcessingStatusUseCase @Inject constructor(
    private val fileDao: FileDao
) {

    suspend operator fun invoke(
        fileId: String,
        status: FileProcessingStatus,
        updatedAt: Long = System.currentTimeMillis()
    ) {
        fileDao.updateProcessingStatus(
            fileId = fileId,
            processingStatus = status,
            updatedAt = updatedAt
        )
    }
}