package com.calibre.android.service.metadata

import com.calibre.android.service.viewer.BookMetadata
import kotlinx.coroutines.flow.Flow

interface IMetadataService {
    suspend fun fetchMetadata(isbn: String?, title: String? = null): BookMetadata
    suspend fun updateMetadata(bookId: String, metadata: BookMetadata)
    fun getCachedMetadata(bookId: String): Flow<BookMetadata?>
    suspend fun clearCache()
    suspend fun getRatings(isbn: String): Ratings?
    suspend fun getSimilarBooks(bookId: String): List<BookMetadata>
}

data class Ratings(
    val averageRating: Float,
    val ratingCount: Int,
    val source: String
)

class MetadataFetchException(message: String) : Exception(message)
