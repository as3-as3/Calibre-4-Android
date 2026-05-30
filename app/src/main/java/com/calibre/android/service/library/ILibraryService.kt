package com.calibre.android.service.library

import kotlinx.coroutines.flow.Flow
import com.calibre.android.model.Book
import com.calibre.android.model.Collection

interface ILibraryService {
    fun getAllBooks(): Flow<List<Book>>
    fun getBooksByFormat(format: String): Flow<List<Book>>
    fun getSeriesBooks(series: String): Flow<List<Book>>
    fun getCollectionBooks(collectionId: String): Flow<List<Book>>
    fun searchBooks(query: String): Flow<List<Book>>
    fun getBook(bookId: String): Flow<Book?>
    fun getRecentBooks(limit: Int = 20): Flow<List<Book>>
    fun getFavorites(): Flow<List<Book>>
    suspend fun getBookCount(): Int
    suspend fun getFormatStats(): Map<String, Int>
    suspend fun addBook(filePath: String): Book
    suspend fun addBooks(filePaths: List<String>): List<Book>
    suspend fun addBookFromUri(uri: String): Book
    suspend fun updateBook(book: Book)
    suspend fun removeBook(bookId: String, deleteFile: Boolean = false)
    suspend fun setFavorite(bookId: String, isFavorite: Boolean)
    suspend fun setArchived(bookId: String, isArchived: Boolean)
    fun getCollections(): Flow<List<Collection>>
    suspend fun createCollection(name: String, color: String = "#FF6200EE"): Collection
    suspend fun addToCollection(bookId: String, collectionId: String)
    suspend fun removeFromCollection(bookId: String, collectionId: String)
    suspend fun deleteCollection(collectionId: String, keepBooks: Boolean = true)
}
