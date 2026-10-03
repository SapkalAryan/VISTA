package com.vista.memoryos.domain.repository

import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import kotlinx.coroutines.flow.Flow

interface FileInventoryRepository {

    fun observeFiles(): Flow<List<VistaFile>>

    fun observeFilesByCategory(
        category: FileCategory
    ): Flow<List<VistaFile>>

    fun observeFilesByUploadStatus(
        status: FileUploadStatus
    ): Flow<List<VistaFile>>

    fun observeFilesByProcessingStatus(
        status: FileProcessingStatus
    ): Flow<List<VistaFile>>

    suspend fun getFile(fileId: String): VistaFile?

    suspend fun saveFile(file: VistaFile)

    suspend fun deleteFile(fileId: String)
}