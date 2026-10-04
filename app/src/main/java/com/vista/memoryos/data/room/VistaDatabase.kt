package com.vista.memoryos.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vista.memoryos.data.room.converter.FileStatusConverters
import com.vista.memoryos.data.room.dao.FileDao
import com.vista.memoryos.data.room.entity.FileEntity
import com.vista.memoryos.data.room.entity.MemoryEntity
import com.vista.memoryos.data.room.entity.FileMemoryEntity
import com.vista.memoryos.data.room.dao.MemoryDao
import com.vista.memoryos.data.room.dao.FileMemoryDao

@Database(
    entities = [
        FileEntity::class,
        MemoryEntity::class,
        FileMemoryEntity::class
    ],
    version = 3,
    exportSchema = true
)
@TypeConverters(FileStatusConverters::class)
abstract class VistaDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao
    abstract fun memoryDao(): MemoryDao
    abstract fun fileMemoryDao(): FileMemoryDao
}