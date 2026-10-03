package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.repository.FileInventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFilesUseCase @Inject constructor(
    private val repository: FileInventoryRepository
) {

    operator fun invoke(): Flow<List<VistaFile>> {
        return repository.observeFiles()
    }
}