package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FileStateValidatorTest {

    private val validator = FileStateValidator()

    @Test
    fun pendingUploadAndPendingProcessing_isNotReadyForProcessing() {
        val file = createFile(
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING
        )

        assertFalse(validator.isReadyForProcessing(file))
    }

    @Test
    fun uploadedAndPendingProcessing_isReadyForProcessing() {
        val file = createFile(
            uploadStatus = FileUploadStatus.UPLOADED,
            processingStatus = FileProcessingStatus.PENDING
        )

        assertTrue(validator.isReadyForProcessing(file))
    }

    @Test
    fun uploadedAndCompleted_isReadyForRetrieval() {
        val file = createFile(
            uploadStatus = FileUploadStatus.UPLOADED,
            processingStatus = FileProcessingStatus.COMPLETED
        )

        assertTrue(validator.isReadyForRetrieval(file))
    }

    @Test
    fun uploadedAndProcessing_isNotReadyForRetrieval() {
        val file = createFile(
            uploadStatus = FileUploadStatus.UPLOADED,
            processingStatus = FileProcessingStatus.PROCESSING
        )

        assertFalse(validator.isReadyForRetrieval(file))
    }

    @Test
    fun failedUpload_isNotReadyForProcessing() {
        val file = createFile(
            uploadStatus = FileUploadStatus.FAILED,
            processingStatus = FileProcessingStatus.PENDING
        )

        assertFalse(validator.isReadyForProcessing(file))
    }

    private fun createFile(
        uploadStatus: FileUploadStatus,
        processingStatus: FileProcessingStatus
    ): VistaFile {
        return VistaFile(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            category = FileCategory.DOCUMENT,
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = uploadStatus,
            processingStatus = processingStatus,
            createdAt = 1000L,
            updatedAt = 1000L
        )
    }
}