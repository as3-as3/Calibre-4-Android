package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.MetadataEntity

@Dao
interface MetadataDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metadata: MetadataEntity): Long
    
    @Query("SELECT * FROM metadata_cache WHERE bookId = :bookId")
    fun getByBookId(bookId: String): Flow<MetadataEntity?>
    
    @Query("""
        SELECT * FROM metadata_cache 
        WHERE isSynced = 0 
        ORDER BY fetchedDate ASC
    """)
    suspend fun getUnsyncedMetadata(): List<MetadataEntity>
    
    @Query("""
        SELECT * FROM metadata_cache 
        WHERE fetchedDate < :expiryTime
        ORDER BY fetchedDate ASC
    """)
    suspend fun getExpiredMetadata(expiryTime: Long): List<MetadataEntity>
    
    @Query("DELETE FROM metadata_cache WHERE expiryDate < :now")
    suspend fun deleteExpiredEntries(now: Long = System.currentTimeMillis()): Int
}
