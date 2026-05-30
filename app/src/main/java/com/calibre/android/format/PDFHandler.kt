package com.calibre.android.format

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import com.calibre.android.model.Chapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class PDFHandler : IFormatHandler {
    override fun supports(file: File): Boolean = file.extension.lowercase() == "pdf"
    override fun supportsFormat(format: String): Boolean = format.lowercase() == "pdf"

    override suspend fun extractMetadata(file: File): BookMetadata? = withContext(Dispatchers.IO) {
        try {
            // Basic metadata extraction
            BookMetadata(
                title = file.nameWithoutExtension,
                author = "Unknown",
                isbn = null,
                publisher = null,
                publishDate = file.lastModified(),
                language = "en",
                description = null,
                series = null,
                seriesIndex = null,
                format = "PDF",
                fileSize = file.length()
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun extractCover(file: File): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val renderer = PdfRenderer(fileDescriptor)
            val page = renderer.openPage(0)
            val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()
            renderer.close()
            fileDescriptor.close()
            bitmap
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun extractTOC(file: File): List<Chapter> = emptyList() // PDF TOC is complex
}
