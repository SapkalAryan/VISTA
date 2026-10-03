package com.vista.memoryos.domain.usecase

import android.net.Uri
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import java.util.UUID
import javax.inject.Inject

class VistaFileFactory @Inject constructor() {

    fun create(
        userId: String,
        sourceUri: Uri,
        displayName: String,
        mimeType: String,
        sizeBytes: Long,
        category: FileCategory,
        now: Long = System.currentTimeMillis()
    ): VistaFile {
        return VistaFile(
            fileId = UUID.randomUUID().toString(),
            userId = userId,
            sourceUri = sourceUri.toString(),
            displayName = displayName,
            mimeType = mimeType,
            category = category,
            sizeBytes = sizeBytes,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = now,
            updatedAt = now
        )
    }
}