package com.vista.memoryos.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus

@Entity(tableName = "files")
data class FileEntity(

    @PrimaryKey
    val fileId: String,

    val userId: String,

    val sourceUri: String?,

    val displayName: String,

    val mimeType: String,

    val sizeBytes: Long,

    val contentHash: String?,

    val cloudPath: String?,

    val uploadStatus: FileUploadStatus,

    val processingStatus: FileProcessingStatus,

    val createdAt: Long,

    val updatedAt: Long
)