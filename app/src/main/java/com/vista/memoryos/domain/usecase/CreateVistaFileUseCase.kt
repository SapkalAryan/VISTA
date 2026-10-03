package com.vista.memoryos.domain.usecase

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.VistaFile
import javax.inject.Inject

class CreateVistaFileUseCase @Inject constructor(
    private val fileCategoryResolver: FileCategoryResolver,
    private val vistaFileFactory: VistaFileFactory
) {

    fun create(
        context: Context,
        userId: String,
        sourceUri: Uri
    ): VistaFile {
        val contentResolver = context.contentResolver

        val mimeType = contentResolver.getType(sourceUri)
            ?: "application/octet-stream"

        var displayName = "Unknown file"
        var sizeBytes = 0L

        contentResolver.query(
            sourceUri,
            arrayOf(
                OpenableColumns.DISPLAY_NAME,
                OpenableColumns.SIZE
            ),
            null,
            null,
            null
        )?.use { cursor ->

            if (cursor.moveToFirst()) {
                val nameIndex =
                    cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)

                val sizeIndex =
                    cursor.getColumnIndex(OpenableColumns.SIZE)

                if (nameIndex >= 0) {
                    displayName = cursor.getString(nameIndex)
                }

                if (sizeIndex >= 0 && !cursor.isNull(sizeIndex)) {
                    sizeBytes = cursor.getLong(sizeIndex)
                }
            }
        }

        val category = fileCategoryResolver.resolve(mimeType)

        return vistaFileFactory.create(
            userId = userId,
            sourceUri = sourceUri,
            displayName = displayName,
            mimeType = mimeType,
            sizeBytes = sizeBytes,
            category = category
        )
    }
}