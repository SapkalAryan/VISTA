package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.FileCategory
import javax.inject.Inject

class FileCategoryResolver @Inject constructor() {

    fun resolve(mimeType: String): FileCategory {
        return when {
            mimeType.startsWith("image/") -> FileCategory.IMAGE

            mimeType.startsWith("video/") -> FileCategory.VIDEO

            mimeType.startsWith("audio/") -> FileCategory.AUDIO

            mimeType == "application/pdf" ||
                    mimeType == "application/msword" ||
                    mimeType == "application/vnd.openxmlformats-officedocument.wordprocessingml.document" ||
                    mimeType == "application/rtf" -> FileCategory.DOCUMENT

            mimeType.startsWith("text/") -> FileCategory.TEXT

            mimeType == "application/vnd.ms-excel" ||
                    mimeType == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" ||
                    mimeType == "text/csv" -> FileCategory.SPREADSHEET

            mimeType == "application/vnd.ms-powerpoint" ||
                    mimeType == "application/vnd.openxmlformats-officedocument.presentationml.presentation" -> FileCategory.PRESENTATION

            mimeType == "application/zip" ||
                    mimeType == "application/x-7z-compressed" ||
                    mimeType == "application/x-rar-compressed" ||
                    mimeType == "application/x-tar" ||
                    mimeType == "application/gzip" -> FileCategory.ARCHIVE

            else -> FileCategory.OTHER
        }
    }
}