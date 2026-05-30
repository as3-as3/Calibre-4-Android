package com.calibre.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.calibre.android.database.entity.*
import com.calibre.android.database.dao.*
import com.calibre.android.database.converter.DateTimeConverters
import com.calibre.android.database.converter.ListConverters

@Database(
    entities = [
        BookEntity::class,
        ReadingProgressEntity::class,
        CollectionEntity::class,
        BookCollectionEntity::class,
        MetadataEntity::class,
        DeviceSyncEntity::class,
        ConversionTaskEntity::class,
        SettingEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverters::class, ListConverters::class)
abstract class CalibreDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun readingProgressDao(): ReadingProgressDao
    abstract fun collectionDao(): CollectionDao
    abstract fun metadataDao(): MetadataDao
    abstract fun deviceSyncDao(): DeviceSyncDao
    abstract fun conversionTaskDao(): ConversionTaskDao
    abstract fun settingDao(): SettingDao
    
    companion object {
        private var INSTANCE: CalibreDatabase? = null
        private const val DATABASE_NAME = "calibre.db"
        
        fun getInstance(context: Context): CalibreDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CalibreDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
