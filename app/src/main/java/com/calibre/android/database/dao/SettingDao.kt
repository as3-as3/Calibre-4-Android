package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.SettingEntity

@Dao
interface SettingDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: SettingEntity)
    
    @Query("SELECT * FROM settings WHERE key = :key")
    fun get(key: String): Flow<SettingEntity?>
    
    @Query("SELECT * FROM settings")
    fun getAll(): Flow<List<SettingEntity>>
    
    @Query("UPDATE settings SET value = :value, lastModified = :timestamp WHERE key = :key")
    suspend fun update(key: String, value: String, timestamp: Long = System.currentTimeMillis())
    
    @Query("DELETE FROM settings WHERE key = :key")
    suspend fun delete(key: String)
}
