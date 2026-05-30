package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.ConversionTaskEntity

@Dao
interface ConversionTaskDao {
    
    @Insert
    suspend fun insert(task: ConversionTaskEntity): Long
    
    @Update
    suspend fun update(task: ConversionTaskEntity): Int
    
    @Delete
    suspend fun delete(task: ConversionTaskEntity): Int
    
    @Query("SELECT * FROM conversion_tasks WHERE id = :taskId")
    fun getById(taskId: String): Flow<ConversionTaskEntity?>
    
    @Query("""
        SELECT * FROM conversion_tasks 
        WHERE status = 'in-progress' 
        ORDER BY createdDate ASC
    """)
    fun getActiveTasks(): Flow<List<ConversionTaskEntity>>
    
    @Query("""
        SELECT * FROM conversion_tasks 
        WHERE status = 'queued'
        ORDER BY createdDate ASC
    """)
    fun getQueuedTasks(): Flow<List<ConversionTaskEntity>>
    
    @Query("""
        UPDATE conversion_tasks 
        SET status = :status, progressPercent = :progress
        WHERE id = :taskId
    """)
    suspend fun updateProgress(taskId: String, status: String, progress: Int)
    
    @Query("""
        UPDATE conversion_tasks 
        SET status = 'failed', errorMessage = :error, completionDate = :date
        WHERE id = :taskId
    """)
    suspend fun markFailed(taskId: String, error: String, date: Long = System.currentTimeMillis())
}
