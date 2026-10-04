package com.vista.memoryos.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vista.memoryos.data.room.entity.FileMemoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FileMemoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(
        relation: FileMemoryEntity
    )

    @Query(
        """
        DELETE FROM file_memory
        WHERE fileId = :fileId
        AND memoryId = :memoryId
        """
    )
    suspend fun delete(
        fileId: String,
        memoryId: String
    )

    @Query(
        """
        DELETE FROM file_memory
        WHERE fileId = :fileId
        """
    )
    suspend fun deleteAllForFile(
        fileId: String
    )

    @Query(
        """
        DELETE FROM file_memory
        WHERE memoryId = :memoryId
        """
    )
    suspend fun deleteAllForMemory(
        memoryId: String
    )

    @Query(
        """
        SELECT memoryId
        FROM file_memory
        WHERE fileId = :fileId
        """
    )
    fun observeMemoryIdsForFile(
        fileId: String
    ): Flow<List<String>>

    @Query(
        """
        SELECT fileId
        FROM file_memory
        WHERE memoryId = :memoryId
        """
    )
    fun observeFileIdsForMemory(
        memoryId: String
    ): Flow<List<String>>
}