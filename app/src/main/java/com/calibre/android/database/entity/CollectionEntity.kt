package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index
import java.util.UUID

/**
 * User-created collections/shelves to organize books
 */
@Entity(
    tableName = "collections",
    indices = [
        Index("name", unique = true),
        Index("createdDate")
    ]
)
data class CollectionEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    val name: String,
    val description: String? = null,
    val color: String = "#FF6200EE",
    val icon: String = "folder",
    
    // Metadata
    val bookCount: Int = 0,
    val displayOrder: Int = 0,
    
    val createdDate: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis()
)
