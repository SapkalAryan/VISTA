package com.vista.memoryos.data.room.converter

import androidx.room.TypeConverter
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus

class FileStatusConverters {

    @TypeConverter
    fun fromUploadStatus(status: FileUploadStatus): String {
        return status.name
    }

    @TypeConverter
    fun toUploadStatus(value: String): FileUploadStatus {
        return FileUploadStatus.valueOf(value)
    }

    @TypeConverter
    fun fromProcessingStatus(status: FileProcessingStatus): String {
        return status.name
    }

    @TypeConverter
    fun toProcessingStatus(value: String): FileProcessingStatus {
        return FileProcessingStatus.valueOf(value)
    }

    @TypeConverter
    fun fromFileCategory(category: FileCategory): String {
        return category.name
    }

    @TypeConverter
    fun toFileCategory(value: String): FileCategory {
        return FileCategory.valueOf(value)
    }
}