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

    /**
     * Room migration:
     *
     * Version 1 -> Version 2
     *
     * Adds the FileCategory column introduced during M2.2.
     */
    private val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(
            db: SupportSQLiteDatabase
        ) {
            db.execSQL(
                """
                ALTER TABLE files
                ADD COLUMN category TEXT NOT NULL DEFAULT 'OTHER'
                """.trimIndent()
            )
        }
    }

    /**
     * Room migration:
     *
     * Version 2 -> Version 3
     *
     * Adds the memory relationship foundation.
     *
     * No existing file columns are modified here.
     */
    private val MIGRATION_2_3 = object : Migration(2, 3) {

        override fun migrate(
            db: SupportSQLiteDatabase
        ) {

            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS memories (
                    memoryId TEXT NOT NULL,
                    userId TEXT NOT NULL,
                    title TEXT,
                    summary TEXT,
                    createdAt INTEGER NOT NULL,
                    updatedAt INTEGER NOT NULL,
                    PRIMARY KEY(memoryId)
                )
                """.trimIndent()
            )

            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS file_memory (
                    fileId TEXT NOT NULL,
                    memoryId TEXT NOT NULL,
                    createdAt INTEGER NOT NULL,
                    PRIMARY KEY(fileId, memoryId),
                    FOREIGN KEY(fileId)
                        REFERENCES files(fileId)
                        ON DELETE CASCADE,
                    FOREIGN KEY(memoryId)
                        REFERENCES memories(memoryId)
                        ON DELETE CASCADE
                )
                """.trimIndent()
            )

            db.execSQL(
                """
                CREATE INDEX IF NOT EXISTS index_memories_userId
                ON memories(userId)
                """.trimIndent()
            )

            db.execSQL(
                """
                CREATE INDEX IF NOT EXISTS index_file_memory_memoryId
                ON file_memory(memoryId)
                """.trimIndent()
            )

            db.execSQL(
                """
                CREATE INDEX IF NOT EXISTS index_file_memory_fileId
                ON file_memory(fileId)
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
            .addMigrations(
                MIGRATION_1_2,
                MIGRATION_2_3
            )
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