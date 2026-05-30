package com.calibre.android.service.library

import android.content.Context
import android.graphics.Bitmap
import com.calibre.android.database.entity.BookEntity
import com.calibre.android.format.FormatHandlerRegistry
import com.calibre.android.model.Book
import com.calibre.android.model.Collection
import com.calibre.android.model.toModel
import com.calibre.android.repository.IBookRepository
import com.calibre.android.util.FileScanner
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryService @Inject constructor(
    private val repository: IBookRepository,
    private val formatRegistry: FormatHandlerRegistry,
    @ApplicationContext private val context: Context
) : ILibraryService {
    
    override fun getAllBooks(): Flow<List<Book>> = repository.getAllBooks().map { it.map { entity -> entity.toModel() } }

    override fun getBooksByFormat(format: String): Flow<List<Book>> {
        TODO("Implement filtering")
    }

    override fun getSeriesBooks(series: String): Flow<List<Book>> {
        TODO("Implement filtering")
    }

    override fun getCollectionBooks(collectionId: String): Flow<List<Book>> {
        TODO("Implement filtering")
    }

    override fun searchBooks(query: String): Flow<List<Book>> = repository.searchBooks(query).map { it.map { entity -> entity.toModel() } }

    override fun getBook(bookId: String): Flow<Book?> = repository.getBookById(bookId).map { it?.toModel() }

    override fun getRecentBooks(limit: Int): Flow<List<Book>> = repository.getAllBooks().map { list ->
        list.sortedByDescending { it.addedDate }.take(limit).map { it.toModel() }
    }

    override fun getFavorites(): Flow<List<Book>> = repository.getAllBooks().map { list ->
        list.filter { it.isFavorite }.map { it.toModel() }
    }

    override suspend fun getBookCount(): Int = withContext(Dispatchers.IO) {
        // Simple implementation for now
        0 
    }

    override suspend fun getFormatStats(): Map<String, Int> = withContext(Dispatchers.IO) {
        emptyMap()
    }

    override suspend fun addBook(filePath: String): Book = withContext(Dispatchers.IO) {
        val file = File(filePath)
        val handler = formatRegistry.getHandler(file) ?: throw Exception("Unsupported format")
        val metadata = handler.extractMetadata(file) ?: throw Exception("Failed to extract metadata")
        
        // Save cover to internal storage
        val coverBitmap = handler.extractCover(file)
        val coverPath = coverBitmap?.let { saveCover(it, UUID.randomUUID().toString()) }

        val entity = BookEntity(
            title = metadata.title,
            author = metadata.author,
            format = metadata.format,
            filePath = file.absolutePath,
            fileSize = metadata.fileSize,
            isbn = metadata.isbn,
            publisher = metadata.publisher,
            publishDate = metadata.publishDate,
            description = metadata.description,
            series = metadata.series,
            seriesIndex = metadata.seriesIndex,
            coverPath = coverPath
        )
        
        repository.addBook(entity)
        entity.toModel()
    }

    private fun saveCover(bitmap: Bitmap, id: String): String {
        val coverFile = File(context.getExternalFilesDir("covers"), "$id.jpg")
        FileOutputStream(coverFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
        return coverFile.absolutePath
    }

    override suspend fun addBooks(filePaths: List<String>): List<Book> = filePaths.map { addBook(it) }

    override suspend fun addBookFromUri(uri: String): Book {
        TODO("Implement URI handling")
    }

    override suspend fun updateBook(book: Book) {
        repository.updateBook(book.toEntity())
    }

    override suspend fun removeBook(bookId: String, deleteFile: Boolean) {
        // Implement logic to delete file if needed
        repository.deleteBook(bookId)
    }

    override suspend fun setFavorite(bookId: String, isFavorite: Boolean) {
        // Get book, update favorite, save
    }

    override suspend fun setArchived(bookId: String, isArchived: Boolean) {
        // Similar to favorite
    }

    override fun getCollections(): Flow<List<Collection>> {
        TODO("Implement collections")
    }

    override suspend fun createCollection(name: String, color: String): Collection {
        TODO("Implement collections")
    }

    override suspend fun addToCollection(bookId: String, collectionId: String) {
        TODO("Implement collections")
    }

    override suspend fun removeFromCollection(bookId: String, collectionId: String) {
        TODO("Implement collections")
    }

    override suspend fun deleteCollection(collectionId: String, keepBooks: Boolean) {
        TODO("Implement collections")
    }
}
