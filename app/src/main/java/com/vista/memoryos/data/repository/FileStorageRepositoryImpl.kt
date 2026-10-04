package com.vista.memoryos.data.repository

import android.content.Context
import android.net.Uri
import com.vista.memoryos.domain.repository.FileStorageRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.upload
import io.ktor.http.ContentType
import javax.inject.Inject

class FileStorageRepositoryImpl @Inject constructor(
    private val context: Context,
    private val supabase: SupabaseClient
) : FileStorageRepository {

    override suspend fun uploadFile(
        userId: String,
        fileId: String,
        sourceUri: Uri,
        contentType: String
    ): String {

        val cloudPath =
            "users/$userId/files/$fileId/original"

        val inputStream =
            context.contentResolver.openInputStream(sourceUri)
                ?: throw IllegalStateException(
                    "Unable to open selected file"
                )

        val storageContentType =
            ContentType.parse(contentType)

        inputStream.use { stream ->

            val bytes = stream.readBytes()

            supabase.storage
                .from("files")
                .upload(cloudPath, bytes) {
                    upsert = false
                    this.contentType = storageContentType
                }
        }

        return cloudPath
    }

    override suspend fun deleteFile(
        cloudPath: String
    ) {
        supabase.storage
            .from("files")
            .delete(cloudPath)
    }
}