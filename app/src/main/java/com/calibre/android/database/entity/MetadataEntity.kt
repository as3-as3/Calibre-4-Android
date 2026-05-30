package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Cached metadata from external sources
 */
@Entity(
    tableName = "metadata_cache",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("bookId", unique = true),
        Index("source"),
        Index("fetchedDate")
    ]
)
data class MetadataEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    val bookId: String,
    
    // Source information
    val source: String,
    val sourceId: String?,
    
    // Extended Metadata
    val googleBooksId: String? = null,
    val openLibraryId: String? = null,
    val calibreId: String? = null,
    
    // Ratings and Reviews
    val averageRating: Float? = null,
    val ratingCount: Int = 0,
    val reviews: String? = null,
    
    // Additional Info
    val genres: String? = null,
    val subjects: String? = null,
    val preview: String? = null,
    
    // Sync Status
    val isSynced: Boolean = false,
    val fetchedDate: Long = System.currentTimeMillis(),
    val expiryDate: Long = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)
)
