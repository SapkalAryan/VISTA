package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.repository.FileInventoryRepository
import javax.inject.Inject

class SaveFileUseCase @Inject constructor(
    private val repository: FileInventoryRepository
) {

    suspend operator fun invoke(file: VistaFile) {
        repository.saveFile(file)
    }
}