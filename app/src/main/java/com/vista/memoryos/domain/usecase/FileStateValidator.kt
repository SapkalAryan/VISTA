package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import javax.inject.Inject

class FileStateValidator @Inject constructor() {

    fun isUploadStateValid(file: VistaFile): Boolean {
        return when (file.uploadStatus) {
            FileUploadStatus.PENDING -> true
            FileUploadStatus.UPLOADING -> true
            FileUploadStatus.UPLOADED -> true
            FileUploadStatus.FAILED -> true
        }
    }

    fun isProcessingStateValid(file: VistaFile): Boolean {
        return when (file.processingStatus) {
            FileProcessingStatus.PENDING -> true
            FileProcessingStatus.PROCESSING -> true
            FileProcessingStatus.COMPLETED -> true
            FileProcessingStatus.FAILED -> true
        }
    }

    fun isReadyForProcessing(file: VistaFile): Boolean {
        return file.uploadStatus == FileUploadStatus.UPLOADED &&
                file.processingStatus == FileProcessingStatus.PENDING
    }

    fun isReadyForRetrieval(file: VistaFile): Boolean {
        return file.uploadStatus == FileUploadStatus.UPLOADED &&
                file.processingStatus == FileProcessingStatus.COMPLETED
    }
}