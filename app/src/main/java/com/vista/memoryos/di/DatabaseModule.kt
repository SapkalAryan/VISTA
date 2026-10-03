package com.vista.memoryos.di

import android.content.Context
import androidx.room.Room
import com.vista.memoryos.data.room.VistaDatabase
import com.vista.memoryos.data.room.dao.FileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVistaDatabase(
        @ApplicationContext context: Context
    ): VistaDatabase {
        return Room.databaseBuilder(
            context,
            VistaDatabase::class.java,
            "vista_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFileDao(
        database: VistaDatabase
    ): FileDao {
        return database.fileDao()
    }
}