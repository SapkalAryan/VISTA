package com.vista.memoryos.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vista.memoryos.data.room.entity.MemoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memory: MemoryEntity)

    @Delete
    suspend fun delete(memory: MemoryEntity)

    @Query(
        """
        SELECT * FROM memories
        WHERE userId = :userId
        ORDER BY updatedAt DESC
        """
    )
    fun observeUserMemories(
        userId: String
    ): Flow<List<MemoryEntity>>

    @Query(
        """
        SELECT * FROM memories
        WHERE memoryId = :memoryId
        LIMIT 1
        """
    )
    suspend fun getById(
        memoryId: String
    ): MemoryEntity?
}