package com.vista.memoryos.data.room

import com.vista.memoryos.data.room.entity.FileEntity
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class VistaDatabaseTest {

    @Test
    fun fileEntity_canBeMappedToExpectedValues() {
        val file = FileEntity(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            category = FileCategory.DOCUMENT,
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = 1000L,
            updatedAt = 1000L
        )

        assertEquals("test-file-id", file.fileId)
        assertEquals("test-user-id", file.userId)
        assertEquals("test.pdf", file.displayName)
        assertEquals(FileCategory.DOCUMENT, file.category)
        assertEquals(FileUploadStatus.PENDING, file.uploadStatus)
        assertEquals(FileProcessingStatus.PENDING, file.processingStatus)
    }
}