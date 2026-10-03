package com.vista.memoryos.data.room.mapper

import com.vista.memoryos.data.room.entity.FileEntity
import com.vista.memoryos.domain.model.VistaFile

fun FileEntity.toDomain(): VistaFile {
    return VistaFile(
        fileId = fileId,
        userId = userId,
        sourceUri = sourceUri,
        displayName = displayName,
        mimeType = mimeType,
        sizeBytes = sizeBytes,
        contentHash = contentHash,
        cloudPath = cloudPath,
        uploadStatus = uploadStatus,
        processingStatus = processingStatus,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun VistaFile.toEntity(): FileEntity {
    return FileEntity(
        fileId = fileId,
        userId = userId,
        sourceUri = sourceUri,
        displayName = displayName,
        mimeType = mimeType,
        sizeBytes = sizeBytes,
        contentHash = contentHash,
        cloudPath = cloudPath,
        uploadStatus = uploadStatus,
        processingStatus = processingStatus,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}