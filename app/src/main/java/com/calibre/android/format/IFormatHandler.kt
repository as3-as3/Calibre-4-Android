package com.calibre.android.format

import android.graphics.Bitmap
import com.calibre.android.model.Book
import com.calibre.android.model.Chapter
import java.io.File

interface IFormatHandler {
    fun supports(file: File): Boolean
    fun supportsFormat(format: String): Boolean
    suspend fun extractMetadata(file: File): BookMetadata?
    suspend fun extractCover(file: File): Bitmap?
    suspend fun extractTOC(file: File): List<Chapter>
}

data class BookMetadata(
    val title: String,
    val author: String?,
    val isbn: String?,
    val publisher: String?,
    val publishDate: Long?,
    val language: String?,
    val description: String?,
    val series: String?,
    val seriesIndex: Float?,
    val format: String,
    val fileSize: Long
)
