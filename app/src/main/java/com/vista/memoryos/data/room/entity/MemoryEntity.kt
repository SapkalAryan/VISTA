package com.vista.memoryos.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "memories",
    indices = [
        Index(value = ["userId"])
    ]
)
data class MemoryEntity(
    @PrimaryKey
    val memoryId: String,
    val userId: String,
    val title: String?,
    val summary: String?,
    val createdAt: Long,
    val updatedAt: Long
)