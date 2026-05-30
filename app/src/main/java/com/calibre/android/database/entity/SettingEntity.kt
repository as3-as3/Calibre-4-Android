package com.calibre.android.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Application settings and user preferences
 */
@Entity(
    tableName = "settings",
    indices = [
        Index("key", unique = true)
    ]
)
data class SettingEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    
    val key: String,
    val value: String,
    val dataType: String = "string",
    
    val lastModified: Long = System.currentTimeMillis()
)

object SettingKeys {
    const val THEME_MODE = "theme_mode"
    const val LIBRARY_PATH = "library_path"
    const val AUTO_SYNC = "auto_sync"
    const val SYNC_INTERVAL_MINUTES = "sync_interval"
    const val PREFERRED_READER = "preferred_reader"
    const val READING_TEXT_SIZE = "reading_text_size"
    const val FONT_FAMILY = "font_family"
    const val CLOUD_SYNC_ENABLED = "cloud_sync"
    const val PRIVACY_MODE = "privacy_mode"
    const val ANALYTICS_ENABLED = "analytics"
}
