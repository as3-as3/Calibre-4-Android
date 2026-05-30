package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index
import java.util.UUID

/**
 * Book represents a single e-book file in the library
 */
@Entity(
    tableName = "books",
    indices = [
        Index("title"),
        Index("author"),
        Index("series"),
        Index("format"),
        Index("addedDate")
    ]
)
data class BookEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    // Metadata
    val title: String,
    val author: String?,
    val series: String?,
    val seriesIndex: Float? = null,
    val isbn: String?,
    val publisher: String?,
    val publishDate: Long?,  // Epoch millis, null if unknown
    val language: String = "en",
    val description: String? = null,
    val rating: Int = 0,  // 0-5 stars
    val tags: String = "",  // Comma-separated
    
    // File Info
    val format: String,  // "EPUB", "PDF", "MOBI", etc.
    val filePath: String,  // Absolute path
    val fileSize: Long,  // Bytes
    val fileHash: String? = null,
    
    // Cover Image
    val coverPath: String? = null,
    val coverUrl: String? = null,
    
    // Timestamps
    val addedDate: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis(),
    val lastReadDate: Long? = null,
    
    // Flags
    val isFavorite: Boolean = false,
    val isArchived: Boolean = false,
    val isHidden: Boolean = false
)
