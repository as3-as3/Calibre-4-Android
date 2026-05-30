package com.calibre.android.format

import android.graphics.Bitmap
import com.calibre.android.model.Chapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class EPUBHandler : IFormatHandler {
    override fun supports(file: File): Boolean = file.extension.lowercase() == "epub"
    override fun supportsFormat(format: String): Boolean = format.lowercase() == "epub"

    override suspend fun extractMetadata(file: File): BookMetadata? = withContext(Dispatchers.IO) {
        // Stub for successful build
        BookMetadata(
            title = file.nameWithoutExtension,
            author = "Unknown",
            isbn = null,
            publisher = null,
            publishDate = null,
            language = "en",
            description = "EPUB Stub",
            series = null,
            seriesIndex = null,
            format = "EPUB",
            fileSize = file.length()
        )
    }

    override suspend fun extractCover(file: File): Bitmap? = null

    override suspend fun extractTOC(file: File): List<Chapter> = emptyList()
}
