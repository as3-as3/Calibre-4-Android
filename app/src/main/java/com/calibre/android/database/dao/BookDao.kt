package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.BookEntity

@Dao
interface BookDao {
    
    @Insert
    suspend fun insert(book: BookEntity): Long
    
    @Insert
    suspend fun insertAll(books: List<BookEntity>)
    
    @Update
    suspend fun update(book: BookEntity): Int
    
    @Delete
    suspend fun delete(book: BookEntity): Int
    
    @Query("DELETE FROM books WHERE id = :bookId")
    suspend fun deleteById(bookId: String): Int
    
    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getById(bookId: String): Flow<BookEntity?>
    
    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): Flow<List<BookEntity>>
    
    @Query("SELECT * FROM books WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavorites(): Flow<List<BookEntity>>
    
    @Query("SELECT * FROM books WHERE isArchived = 0 ORDER BY addedDate DESC LIMIT :limit")
    fun getRecentBooks(limit: Int = 20): Flow<List<BookEntity>>
    
    @Query("""
        SELECT * FROM books 
        WHERE title LIKE '%' || :query || '%' 
           OR author LIKE '%' || :query || '%'
           OR series LIKE '%' || :query || '%'
        ORDER BY title ASC
    """)
    fun searchBooks(query: String): Flow<List<BookEntity>>
    
    @Query("SELECT * FROM books WHERE format = :format ORDER BY title ASC")
    fun getByFormat(format: String): Flow<List<BookEntity>>
    
    @Query("SELECT * FROM books WHERE series = :series ORDER BY seriesIndex ASC")
    fun getSeriesBooks(series: String): Flow<List<BookEntity>>
    
    @Query("SELECT COUNT(*) FROM books")
    suspend fun getBookCount(): Int
    
    @Query("SELECT COUNT(*) FROM books WHERE format = :format")
    suspend fun getCountByFormat(format: String): Int
    
    @Query("SELECT DISTINCT format FROM books ORDER BY format ASC")
    suspend fun getAllFormats(): List<String>
    
    @Query("""
        SELECT * FROM books 
        WHERE addedDate >= :startDate AND addedDate <= :endDate
        ORDER BY addedDate DESC
    """)
    suspend fun getBooksAddedBetween(startDate: Long, endDate: Long): List<BookEntity>
}
