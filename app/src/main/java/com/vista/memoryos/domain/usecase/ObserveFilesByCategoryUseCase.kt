package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.repository.FileInventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFilesByCategoryUseCase @Inject constructor(
    private val repository: FileInventoryRepository
) {

    operator fun invoke(
        category: FileCategory
    ): Flow<List<VistaFile>> {
        return repository.observeFilesByCategory(category)
    }
}