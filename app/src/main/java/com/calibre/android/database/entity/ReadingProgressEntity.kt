package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Tracks reading progress per book
 */
@Entity(
    tableName = "reading_progress",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["bookId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("bookId"),
        Index("lastReadDate")
    ]
)
data class ReadingProgressEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    val bookId: String,
    
    // EPUB/PDF specific progress
    val currentChapter: Int = 0,
    val currentPage: Int = 0,
    val currentHref: String? = null,
    val currentOffset: Long = 0,
    
    // Progress percentage (0-100)
    val progressPercent: Float = 0f,
    
    // Reading Statistics
    val totalTimeMinutes: Long = 0,
    val currentSessionStartMs: Long? = null,
    val averagePaceWordsPerMinute: Int = 0,
    
    // Timestamps
    val firstReadDate: Long = System.currentTimeMillis(),
    val lastReadDate: Long = System.currentTimeMillis(),
    val completionDate: Long? = null,
    
    // Status
    val status: String = "reading",  // "not-started", "reading", "paused", "completed"
    val isBookmarked: Boolean = false
)
