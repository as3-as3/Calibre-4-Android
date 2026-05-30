package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.ReadingProgressEntity

@Dao
interface ReadingProgressDao {
    
    @Insert
    suspend fun insert(progress: ReadingProgressEntity): Long
    
    @Update
    suspend fun update(progress: ReadingProgressEntity): Int
    
    @Query("SELECT * FROM reading_progress WHERE bookId = :bookId")
    fun getByBookId(bookId: String): Flow<ReadingProgressEntity?>
    
    @Query("""
        SELECT * FROM reading_progress 
        WHERE status = 'reading' 
        ORDER BY lastReadDate DESC
    """)
    fun getCurrentlyReading(): Flow<List<ReadingProgressEntity>>
    
    @Query("""
        SELECT * FROM reading_progress 
        WHERE status = 'completed'
        ORDER BY completionDate DESC
    """)
    fun getCompleted(): Flow<List<ReadingProgressEntity>>
    
    @Query("""
        UPDATE reading_progress 
        SET currentPage = :page, 
            progressPercent = :percent,
            lastReadDate = :timestamp
        WHERE bookId = :bookId
    """)
    suspend fun updateProgress(
        bookId: String,
        page: Int,
        percent: Float,
        timestamp: Long = System.currentTimeMillis()
    )
    
    @Query("""
        UPDATE reading_progress 
        SET status = :status, completionDate = :date
        WHERE bookId = :bookId
    """)
    suspend fun updateStatus(bookId: String, status: String, date: Long)
}
