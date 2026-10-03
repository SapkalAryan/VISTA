package com.vista.memoryos.domain.model

data class VistaFile(
    val fileId: String,
    val userId: String,
    val sourceUri: String?,
    val displayName: String,
    val mimeType: String,
    val category: FileCategory,
    val sizeBytes: Long,
    val contentHash: String?,
    val cloudPath: String?,
    val uploadStatus: FileUploadStatus,
    val processingStatus: FileProcessingStatus,
    val createdAt: Long,
    val updatedAt: Long
)