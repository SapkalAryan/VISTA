package com.vista.memoryos.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vista.memoryos.data.room.entity.FileEntity
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface FileDao {

    @Query("SELECT * FROM files ORDER BY createdAt DESC")
    fun observeAllFiles(): Flow<List<FileEntity>>

    @Query("SELECT * FROM files WHERE fileId = :fileId LIMIT 1")
    suspend fun getFileById(fileId: String): FileEntity?

    @Query(
        "SELECT * FROM files WHERE category = :category ORDER BY createdAt DESC"
    )
    fun observeFilesByCategory(
        category: FileCategory
    ): Flow<List<FileEntity>>

    @Query(
        """
        SELECT * FROM files
        WHERE uploadStatus = :uploadStatus
        ORDER BY createdAt DESC
        """
    )
    fun observeFilesByUploadStatus(
        uploadStatus: FileUploadStatus
    ): Flow<List<FileEntity>>

    @Query(
        """
        SELECT * FROM files
        WHERE processingStatus = :processingStatus
        ORDER BY createdAt DESC
        """
    )
    fun observeFilesByProcessingStatus(
        processingStatus: FileProcessingStatus
    ): Flow<List<FileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: FileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiles(files: List<FileEntity>)

    @Update
    suspend fun updateFile(file: FileEntity)

    @Delete
    suspend fun deleteFile(file: FileEntity)

    @Query("DELETE FROM files WHERE fileId = :fileId")
    suspend fun deleteFileById(fileId: String)

    @Query(
        """
        UPDATE files
        SET uploadStatus = :uploadStatus,
            updatedAt = :updatedAt
        WHERE fileId = :fileId
        """
    )
    suspend fun updateUploadStatus(
        fileId: String,
        uploadStatus: FileUploadStatus,
        updatedAt: Long
    )

    @Query(
        """
        UPDATE files
        SET processingStatus = :processingStatus,
            updatedAt = :updatedAt
        WHERE fileId = :fileId
        """
    )
    suspend fun updateProcessingStatus(
        fileId: String,
        processingStatus: FileProcessingStatus,
        updatedAt: Long
    )
}