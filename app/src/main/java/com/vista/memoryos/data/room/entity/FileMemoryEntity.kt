package com.vista.memoryos.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "file_memory",
    primaryKeys = ["fileId", "memoryId"],
    indices = [
        Index(value = ["fileId"]),
        Index(value = ["memoryId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = FileEntity::class,
            parentColumns = ["fileId"],
            childColumns = ["fileId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MemoryEntity::class,
            parentColumns = ["memoryId"],
            childColumns = ["memoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FileMemoryEntity(
    val fileId: String,
    val memoryId: String,
    val createdAt: Long
)