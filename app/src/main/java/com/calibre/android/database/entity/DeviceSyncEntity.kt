package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Tracks synchronization history with external devices
 */
@Entity(
    tableName = "device_sync_history",
    indices = [
        Index("deviceId"),
        Index("bookId"),
        Index("syncDate")
    ]
)
data class DeviceSyncEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Device Info
    val deviceId: String,
    val deviceName: String,
    val deviceType: String,
    
    // Book Transfer
    val bookId: String,
    val transferStatus: String,
    val transferProgress: Int = 0,
    val errorMessage: String? = null,
    
    // Format Conversion
    val convertedFormat: String? = null,
    val originalFormat: String? = null,
    
    // Timestamps
    val syncDate: Long = System.currentTimeMillis(),
    val completionDate: Long? = null,
    
    // Metadata
    val bytesTransferred: Long = 0,
    val duration: Long = 0
)
