package com.calibre.android.service.metadata

import com.calibre.android.database.dao.BookDao
import com.calibre.android.database.dao.MetadataDao
import com.calibre.android.database.entity.MetadataEntity
import com.calibre.android.service.viewer.BookMetadata
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MetadataService @Inject constructor(
    private val bookDao: BookDao,
    private val metadataDao: MetadataDao
) : IMetadataService {
    override suspend fun fetchMetadata(isbn: String?, title: String?): BookMetadata {
        // Phase 4: Implementation of external API calls (Google Books etc.)
        return BookMetadata(
            title = title ?: "Unknown",
            author = "Unknown",
            publisher = null,
            publishDate = null,
            language = "en",
            description = "Metadata fetched for $isbn",
            isbn = isbn
        )
    }

    override suspend fun updateMetadata(bookId: String, metadata: BookMetadata) {
        val book = bookDao.getById(bookId)
        book?.let {
            val updated = it.copy(
                title = metadata.title,
                author = metadata.author,
                isbn = metadata.isbn,
                publisher = metadata.publisher,
                description = metadata.description
            )
            bookDao.update(updated)
        }
    }

    override fun getCachedMetadata(bookId: String): Flow<BookMetadata?> {
        return metadataDao.getByBookId(bookId).map { it?.let { entity ->
            BookMetadata(
                title = "", // Should map correctly
                author = "",
                publisher = null,
                publishDate = null,
                language = null,
                description = "",
                isbn = null
            )
        } }
    }

    override suspend fun clearCache() {
        metadataDao.deleteExpiredEntries(System.currentTimeMillis() + 100000000)
    }

    override suspend fun getRatings(isbn: String): Ratings? {
        return Ratings(4.5f, 100, "MockSource")
    }

    override suspend fun getSimilarBooks(bookId: String): List<BookMetadata> {
        return emptyList()
    }
}
