package com.calibre.android.repository

import com.calibre.android.database.dao.BookDao
import com.calibre.android.database.entity.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IBookRepository {
    fun getAllBooks(): Flow<List<BookEntity>>
    fun getBookById(id: String): Flow<BookEntity?>
    fun searchBooks(query: String): Flow<List<BookEntity>>
    suspend fun addBook(book: BookEntity)
    suspend fun updateBook(book: BookEntity)
    suspend fun deleteBook(id: String)
}

@Singleton
class BookRepository @Inject constructor(
    private val bookDao: BookDao
) : IBookRepository {
    
    override fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()
    
    override fun getBookById(id: String): Flow<BookEntity?> = 
        bookDao.getById(id)
    
    override fun searchBooks(query: String): Flow<List<BookEntity>> = 
        bookDao.searchBooks(query)
    
    override suspend fun addBook(book: BookEntity) = withContext(Dispatchers.IO) {
        bookDao.insert(book)
    }
    
    override suspend fun updateBook(book: BookEntity) = withContext(Dispatchers.IO) {
        bookDao.update(book)
    }
    
    override suspend fun deleteBook(id: String) = withContext(Dispatchers.IO) {
        bookDao.deleteById(id)
    }
}
