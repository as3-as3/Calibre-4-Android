package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.DeviceSyncEntity

@Dao
interface DeviceSyncDao {
    @Insert
    suspend fun insert(sync: DeviceSyncEntity): Long
    
    @Update
    suspend fun update(sync: DeviceSyncEntity): Int
    
    @Query("SELECT * FROM device_sync_history ORDER BY syncDate DESC")
    fun getAllSyncHistory(): Flow<List<DeviceSyncEntity>>
    
    @Query("SELECT * FROM device_sync_history WHERE deviceId = :deviceId ORDER BY syncDate DESC")
    fun getSyncHistoryByDevice(deviceId: String): Flow<List<DeviceSyncEntity>>
}
