package com.vista.memoryos.data.repository

import com.vista.memoryos.data.room.dao.FileDao
import com.vista.memoryos.data.room.mapper.toDomain
import com.vista.memoryos.data.room.mapper.toEntity
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.repository.FileInventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FileInventoryRepositoryImpl @Inject constructor(
    private val fileDao: FileDao
) : FileInventoryRepository {

    override fun observeFiles(): Flow<List<VistaFile>> {
        return fileDao.observeAllFiles()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun observeFilesByCategory(
        category: FileCategory
    ): Flow<List<VistaFile>> {
        return fileDao.observeFilesByCategory(category)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun observeFilesByUploadStatus(
        status: FileUploadStatus
    ): Flow<List<VistaFile>> {
        return fileDao.observeFilesByUploadStatus(status)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun observeFilesByProcessingStatus(
        status: FileProcessingStatus
    ): Flow<List<VistaFile>> {
        return fileDao.observeFilesByProcessingStatus(status)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun getFile(fileId: String): VistaFile? {
        return fileDao.getFileById(fileId)?.toDomain()
    }

    override suspend fun saveFile(file: VistaFile) {
        fileDao.insertFile(file.toEntity())
    }

    override suspend fun deleteFile(fileId: String) {
        fileDao.deleteFileById(fileId)
    }
}