package com.vista.memoryos.domain.usecase

import com.vista.memoryos.domain.model.FileCategory
import org.junit.Assert.assertEquals
import org.junit.Test

class FileCategoryResolverTest {

    private val resolver = FileCategoryResolver()

    @Test
    fun imageMimeType_resolvesToImage() {
        assertEquals(
            FileCategory.IMAGE,
            resolver.resolve("image/jpeg")
        )
    }

    @Test
    fun videoMimeType_resolvesToVideo() {
        assertEquals(
            FileCategory.VIDEO,
            resolver.resolve("video/mp4")
        )
    }

    @Test
    fun audioMimeType_resolvesToAudio() {
        assertEquals(
            FileCategory.AUDIO,
            resolver.resolve("audio/mpeg")
        )
    }

    @Test
    fun pdfMimeType_resolvesToDocument() {
        assertEquals(
            FileCategory.DOCUMENT,
            resolver.resolve("application/pdf")
        )
    }

    @Test
    fun textMimeType_resolvesToText() {
        assertEquals(
            FileCategory.TEXT,
            resolver.resolve("text/plain")
        )
    }

    @Test
    fun spreadsheetMimeType_resolvesToSpreadsheet() {
        assertEquals(
            FileCategory.SPREADSHEET,
            resolver.resolve(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
        )
    }

    @Test
    fun presentationMimeType_resolvesToPresentation() {
        assertEquals(
            FileCategory.PRESENTATION,
            resolver.resolve(
                "application/vnd.openxmlformats-officedocument.presentationml.presentation"
            )
        )
    }

    @Test
    fun archiveMimeType_resolvesToArchive() {
        assertEquals(
            FileCategory.ARCHIVE,
            resolver.resolve("application/zip")
        )
    }

    @Test
    fun unknownMimeType_resolvesToOther() {
        assertEquals(
            FileCategory.OTHER,
            resolver.resolve("application/octet-stream")
        )
    }
}