package com.calibre.android.service.viewer

import android.graphics.Bitmap
import com.calibre.android.model.Chapter
import com.calibre.android.model.ReadingProgress
import com.calibre.android.model.SearchMatch
import kotlinx.coroutines.flow.Flow

interface IBookViewerService {
    suspend fun openBook(bookId: String): BookViewer
    suspend fun closeBook(bookId: String)
    suspend fun getTableOfContents(bookId: String): List<Chapter>
    suspend fun getChapterContent(bookId: String, chapterIndex: Int): String
    suspend fun getPageImage(bookId: String, pageNumber: Int, width: Int, height: Int): Bitmap?
    suspend fun getMetadata(bookId: String): BookMetadata
    suspend fun getCover(bookId: String, width: Int, height: Int): Bitmap?
    fun getReadingProgress(bookId: String): Flow<ReadingProgress?>
    suspend fun updatePosition(bookId: String, chapterIndex: Int = 0, pageNumber: Int = 0, href: String? = null)
    suspend fun markCompleted(bookId: String)
    suspend fun getReadingStats(bookId: String): ReadingStats
    suspend fun searchInBook(bookId: String, query: String): List<SearchMatch>
    suspend fun getPageCount(bookId: String): Int
    suspend fun navigateTo(bookId: String, location: String)
}

data class BookViewer(
    val bookId: String,
    val format: String,
    val pageCount: Int,
    val chapters: List<Chapter> = emptyList()
)

data class BookMetadata(
    val title: String,
    val author: String?,
    val publisher: String?,
    val publishDate: String?,
    val language: String?,
    val description: String?,
    val isbn: String?
)

data class ReadingStats(
    val totalTimeMinutes: Long,
    val averagePaceWordsPerMinute: Int,
    val sessionsCount: Int,
    val lastSessionMinutes: Long
)
