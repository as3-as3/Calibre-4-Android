package com.calibre.android.service.viewer

import android.graphics.Bitmap
import com.calibre.android.model.Chapter
import com.calibre.android.model.ReadingProgress
import com.calibre.android.model.SearchMatch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookViewerService @Inject constructor() : IBookViewerService {
    override suspend fun openBook(bookId: String): BookViewer {
        TODO("Not yet implemented")
    }

    override suspend fun closeBook(bookId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTableOfContents(bookId: String): List<Chapter> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterContent(bookId: String, chapterIndex: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun getPageImage(bookId: String, pageNumber: Int, width: Int, height: Int): Bitmap? {
        TODO("Not yet implemented")
    }

    override suspend fun getMetadata(bookId: String): BookMetadata {
        TODO("Not yet implemented")
    }

    override suspend fun getCover(bookId: String, width: Int, height: Int): Bitmap? {
        TODO("Not yet implemented")
    }

    override fun getReadingProgress(bookId: String): Flow<ReadingProgress?> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePosition(bookId: String, chapterIndex: Int, pageNumber: Int, href: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun markCompleted(bookId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getReadingStats(bookId: String): ReadingStats {
        TODO("Not yet implemented")
    }

    override suspend fun searchInBook(bookId: String, query: String): List<SearchMatch> {
        TODO("Not yet implemented")
    }

    override suspend fun getPageCount(bookId: String): Int {
        TODO("Not yet implemented")
    }

    override suspend fun navigateTo(bookId: String, location: String) {
        TODO("Not yet implemented")
    }
}
