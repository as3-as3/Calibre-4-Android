package com.calibre.android.format

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.calibre.android.model.Chapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.siegmann.epublib.epub.EpubReader
import java.io.File
import java.io.FileInputStream

class EPUBHandler : IFormatHandler {
    override fun supports(file: File): Boolean = file.extension.lowercase() == "epub"
    override fun supportsFormat(format: String): Boolean = format.lowercase() == "epub"

    override suspend fun extractMetadata(file: File): BookMetadata? = withContext(Dispatchers.IO) {
        try {
            val epubReader = EpubReader()
            val book = epubReader.readEpub(FileInputStream(file))
            val metadata = book.metadata
            
            BookMetadata(
                title = metadata.firstTitle ?: file.nameWithoutExtension,
                author = metadata.authors.firstOrNull()?.let { "${it.firstname} ${it.lastname}" } ?: "Unknown",
                isbn = metadata.identifiers.firstOrNull()?.value,
                publisher = metadata.publishers.firstOrNull(),
                publishDate = null, // Parsing dates is tricky
                language = metadata.language,
                description = metadata.descriptions.firstOrNull(),
                series = null,
                seriesIndex = null,
                format = "EPUB",
                fileSize = file.length()
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun extractCover(file: File): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val epubReader = EpubReader()
            val book = epubReader.readEpub(FileInputStream(file))
            val coverImage = book.coverImage
            if (coverImage != null) {
                BitmapFactory.decodeStream(coverImage.inputStream)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun extractTOC(file: File): List<Chapter> = withContext(Dispatchers.IO) {
        try {
            val epubReader = EpubReader()
            val book = epubReader.readEpub(FileInputStream(file))
            book.tableOfContents.tocReferences.mapIndexed { index, ref ->
                Chapter(
                    title = ref.title,
                    href = ref.completeHref,
                    chapterIndex = index
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
