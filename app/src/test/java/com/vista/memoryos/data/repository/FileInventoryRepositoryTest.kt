package com.vista.memoryos.data.repository

import com.vista.memoryos.data.room.dao.FileDao
import com.vista.memoryos.data.room.entity.FileEntity
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class FileInventoryRepositoryTest {

    @Test
    fun saveFile_convertsDomainModelToEntity() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        val file = TestFileFactory.create()

        repository.saveFile(file)

        assertNotNull(dao.savedFile)
        assertEquals(file.fileId, dao.savedFile?.fileId)
        assertEquals(file.displayName, dao.savedFile?.displayName)
        assertEquals(file.category, dao.savedFile?.category)
        assertEquals(file.uploadStatus, dao.savedFile?.uploadStatus)
        assertEquals(file.processingStatus, dao.savedFile?.processingStatus)
    }

    @Test
    fun getFile_convertsEntityToDomainModel() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        val entity = TestFileFactory.createEntity()
        dao.savedFile = entity

        val result = repository.getFile(entity.fileId)

        assertNotNull(result)
        assertEquals(entity.fileId, result?.fileId)
        assertEquals(entity.displayName, result?.displayName)
        assertEquals(entity.category, result?.category)
        assertEquals(entity.uploadStatus, result?.uploadStatus)
        assertEquals(entity.processingStatus, result?.processingStatus)
    }

    @Test
    fun observeFiles_convertsEntitiesToDomainModels() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        dao.files = listOf(
            TestFileFactory.createEntity()
        )

        val result = repository.observeFiles()

        var observedFiles =
            emptyList<com.vista.memoryos.domain.model.VistaFile>()

        result.collect {
            observedFiles = it
        }

        assertEquals(1, observedFiles.size)
        assertEquals(
            dao.files.first().fileId,
            observedFiles.first().fileId
        )
    }

    @Test
    fun deleteFile_deletesByVistaId() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        repository.deleteFile("test-file-id")

        assertEquals("test-file-id", dao.deletedFileId)
    }

    @Test
    fun observeFilesByCategory_returnsMatchingFiles() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        dao.files = listOf(
            TestFileFactory.createEntity(),
            TestFileFactory.createEntity().copy(
                fileId = "image-file-id",
                displayName = "image.jpg",
                mimeType = "image/jpeg",
                category = FileCategory.IMAGE
            )
        )

        val result = repository
            .observeFilesByCategory(FileCategory.IMAGE)

        var observedFiles =
            emptyList<com.vista.memoryos.domain.model.VistaFile>()

        result.collect {
            observedFiles = it
        }

        assertEquals(1, observedFiles.size)
        assertEquals("image-file-id", observedFiles.first().fileId)
    }

    @Test
    fun observeFilesByUploadStatus_returnsMatchingFiles() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        dao.files = listOf(
            TestFileFactory.createEntity(),
            TestFileFactory.createEntity().copy(
                fileId = "uploaded-file-id",
                uploadStatus = FileUploadStatus.UPLOADED
            )
        )

        val result = repository
            .observeFilesByUploadStatus(FileUploadStatus.UPLOADED)

        var observedFiles =
            emptyList<com.vista.memoryos.domain.model.VistaFile>()

        result.collect {
            observedFiles = it
        }

        assertEquals(1, observedFiles.size)
        assertEquals("uploaded-file-id", observedFiles.first().fileId)
    }

    @Test
    fun observeFilesByProcessingStatus_returnsMatchingFiles() = runBlocking {
        val dao = FakeFileDao()
        val repository = FileInventoryRepositoryImpl(dao)

        dao.files = listOf(
            TestFileFactory.createEntity(),
            TestFileFactory.createEntity().copy(
                fileId = "completed-file-id",
                processingStatus = FileProcessingStatus.COMPLETED
            )
        )

        val result = repository
            .observeFilesByProcessingStatus(FileProcessingStatus.COMPLETED)

        var observedFiles =
            emptyList<com.vista.memoryos.domain.model.VistaFile>()

        result.collect {
            observedFiles = it
        }

        assertEquals(1, observedFiles.size)
        assertEquals("completed-file-id", observedFiles.first().fileId)
    }
}

private object TestFileFactory {

    fun create() = com.vista.memoryos.domain.model.VistaFile(
        fileId = "test-file-id",
        userId = "test-user-id",
        sourceUri = "content://test/file",
        displayName = "test.pdf",
        mimeType = "application/pdf",
        category = FileCategory.DOCUMENT,
        sizeBytes = 1024L,
        contentHash = null,
        cloudPath = null,
        uploadStatus = FileUploadStatus.PENDING,
        processingStatus = FileProcessingStatus.PENDING,
        createdAt = 1000L,
        updatedAt = 1000L
    )

    fun createEntity() = FileEntity(
        fileId = "test-file-id",
        userId = "test-user-id",
        sourceUri = "content://test/file",
        displayName = "test.pdf",
        mimeType = "application/pdf",
        category = FileCategory.DOCUMENT,
        sizeBytes = 1024L,
        contentHash = null,
        cloudPath = null,
        uploadStatus = FileUploadStatus.PENDING,
        processingStatus = FileProcessingStatus.PENDING,
        createdAt = 1000L,
        updatedAt = 1000L
    )
}

private class FakeFileDao : FileDao {

    var savedFile: FileEntity? = null
    var files: List<FileEntity> = emptyList()
    var deletedFileId: String? = null

    override fun observeAllFiles(): Flow<List<FileEntity>> {
        return flowOf(files)
    }

    override fun observeFilesByCategory(
        category: FileCategory
    ): Flow<List<FileEntity>> {
        return flowOf(
            files.filter { it.category == category }
        )
    }

    override fun observeFilesByUploadStatus(
        uploadStatus: FileUploadStatus
    ): Flow<List<FileEntity>> {
        return flowOf(
            files.filter { it.uploadStatus == uploadStatus }
        )
    }

    override fun observeFilesByProcessingStatus(
        processingStatus: FileProcessingStatus
    ): Flow<List<FileEntity>> {
        return flowOf(
            files.filter { it.processingStatus == processingStatus }
        )
    }

    override suspend fun getFileById(
        fileId: String
    ): FileEntity? {
        return savedFile?.takeIf { it.fileId == fileId }
    }

    override suspend fun insertFile(
        file: FileEntity
    ) {
        savedFile = file
    }

    override suspend fun insertFiles(
        files: List<FileEntity>
    ) {
        this.files = files
    }

    override suspend fun updateFile(
        file: FileEntity
    ) {
        savedFile = file
    }

    override suspend fun deleteFile(
        file: FileEntity
    ) {
        deletedFileId = file.fileId
    }

    override suspend fun deleteFileById(
        fileId: String
    ) {
        deletedFileId = fileId
    }

    override suspend fun updateUploadStatus(
        fileId: String,
        uploadStatus: FileUploadStatus,
        updatedAt: Long
    ) {
        savedFile = savedFile?.copy(
            uploadStatus = uploadStatus,
            updatedAt = updatedAt
        )
    }

    override suspend fun updateProcessingStatus(
        fileId: String,
        processingStatus: FileProcessingStatus,
        updatedAt: Long
    ) {
        savedFile = savedFile?.copy(
            processingStatus = processingStatus,
            updatedAt = updatedAt
        )
    }
}