package com.vista.memoryos.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vista.memoryos.data.room.converter.FileStatusConverters
import com.vista.memoryos.data.room.dao.FileDao
import com.vista.memoryos.data.room.entity.FileEntity

@Database(
    entities = [
        FileEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(FileStatusConverters::class)
abstract class VistaDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao
}