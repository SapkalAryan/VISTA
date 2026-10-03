package com.vista.memoryos.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    private val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
                ALTER TABLE files
                ADD COLUMN category TEXT NOT NULL DEFAULT 'OTHER'
                """.trimIndent()
            )
        }
    }

    @Provides
    @Singleton
    fun provideVistaDatabase(
        @ApplicationContext context: Context
    ): VistaDatabase {
        return Room.databaseBuilder(
            context,
            VistaDatabase::class.java,
            "vista_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideFileDao(
        database: VistaDatabase
    ): FileDao {
        return database.fileDao()
    }
}