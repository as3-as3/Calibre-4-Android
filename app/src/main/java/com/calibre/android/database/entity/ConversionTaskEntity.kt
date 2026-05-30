package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Tracks format conversion tasks for background processing
 */
@Entity(
    tableName = "conversion_tasks",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("bookId"),
        Index("status"),
        Index("createdDate")
    ]
)
data class ConversionTaskEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Source Book
    val bookId: String,
    val sourceFormat: String,
    val sourceFilePath: String,
    
    // Target Format
    val targetFormat: String,
    val targetFilePath: String,
    
    // Conversion Options
    val compressionLevel: Int = 6,
    val imageQuality: Int = 85,
    val fontSize: Int = 12,
    val stripDRM: Boolean = false,
    
    // Status
    val status: String,
    val progressPercent: Int = 0,
    val errorMessage: String? = null,
    
    // Timestamps
    val createdDate: Long = System.currentTimeMillis(),
    val startedDate: Long? = null,
    val completionDate: Long? = null,
    val duration: Long = 0
)
