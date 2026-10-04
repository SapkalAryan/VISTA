package com.vista.memoryos.domain.repository

import android.net.Uri

interface FileStorageRepository {

    suspend fun uploadFile(
        userId: String,
        fileId: String,
        sourceUri: Uri,
        contentType: String
    ): String

    suspend fun deleteFile(
        cloudPath: String
    )
}