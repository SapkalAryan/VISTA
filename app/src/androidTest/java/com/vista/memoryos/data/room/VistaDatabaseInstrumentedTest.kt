package com.vista.memoryos.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vista.memoryos.data.room.entity.FileEntity
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.vista.memoryos.domain.model.FileCategory

@RunWith(AndroidJUnit4::class)
class VistaDatabaseInstrumentedTest {

    private lateinit var database: VistaDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            VistaDatabase::class.java
        ).build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun fileCanBeInsertedAndRetrieved() = runBlocking {
        val file = FileEntity(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = 1000L,
            category = FileCategory.DOCUMENT,
            updatedAt = 1000L
        )

        val dao = database.fileDao()

        dao.insertFile(file)

        val retrievedFile = dao.getFileById("test-file-id")

        assertNotNull(retrievedFile)
        assertEquals(file, retrievedFile)
    }

    @Test
    fun filesCanBeObserved() = runBlocking {
        val file = FileEntity(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = 1000L,
            category = FileCategory.DOCUMENT,
            updatedAt = 1000L
        )

        val dao = database.fileDao()

        dao.insertFile(file)

        val files = dao.observeAllFiles().first()

        assertEquals(1, files.size)
        assertEquals("test-file-id", files.first().fileId)
    }

    @Test
    fun uploadStatusCanBeUpdated() = runBlocking {
        val file = FileEntity(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = 1000L,
            category = FileCategory.DOCUMENT,
            updatedAt = 1000L
        )

        val dao = database.fileDao()

        dao.insertFile(file)

        dao.updateUploadStatus(
            fileId = "test-file-id",
            uploadStatus = FileUploadStatus.UPLOADED,
            updatedAt = 2000L
        )

        val updatedFile = dao.getFileById("test-file-id")

        assertNotNull(updatedFile)
        assertEquals(FileUploadStatus.UPLOADED, updatedFile?.uploadStatus)
        assertEquals(2000L, updatedFile?.updatedAt)
    }

    @Test
    fun processingStatusCanBeUpdated() = runBlocking {
        val file = FileEntity(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.UPLOADED,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = 1000L,
            category = FileCategory.DOCUMENT,
            updatedAt = 1000L
        )

        val dao = database.fileDao()

        dao.insertFile(file)

        dao.updateProcessingStatus(
            fileId = "test-file-id",
            processingStatus = FileProcessingStatus.COMPLETED,
            updatedAt = 3000L
        )

        val updatedFile = dao.getFileById("test-file-id")

        assertNotNull(updatedFile)
        assertEquals(
            FileProcessingStatus.COMPLETED,
            updatedFile?.processingStatus
        )
        assertEquals(3000L, updatedFile?.updatedAt)
    }

    @Test
    fun fileCanBeDeleted() = runBlocking {
        val file = FileEntity(
            fileId = "test-file-id",
            userId = "test-user-id",
            sourceUri = "content://test/file",
            displayName = "test.pdf",
            mimeType = "application/pdf",
            sizeBytes = 1024L,
            contentHash = null,
            cloudPath = null,
            uploadStatus = FileUploadStatus.PENDING,
            processingStatus = FileProcessingStatus.PENDING,
            createdAt = 1000L,
            category = FileCategory.DOCUMENT,
            updatedAt = 1000L
        )

        val dao = database.fileDao()

        dao.insertFile(file)
        dao.deleteFileById("test-file-id")

        val deletedFile = dao.getFileById("test-file-id")

        assertEquals(null, deletedFile)
    }
}