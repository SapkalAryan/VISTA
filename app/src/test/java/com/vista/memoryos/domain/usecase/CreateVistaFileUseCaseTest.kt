package com.vista.memoryos.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CreateVistaFileUseCaseTest {

    @Test
    fun createUseCase_canBeConstructed() {
        val categoryResolver = FileCategoryResolver()
        val fileFactory = VistaFileFactory()

        val useCase = CreateVistaFileUseCase(
            fileCategoryResolver = categoryResolver,
            vistaFileFactory = fileFactory
        )

        assertNotNull(useCase)
    }

    @Test
    fun categoryResolver_supportsPdfForInventoryCreation() {
        val resolver = FileCategoryResolver()

        val category = resolver.resolve("application/pdf")

        assertEquals(
            com.vista.memoryos.domain.model.FileCategory.DOCUMENT,
            category
        )
    }
}