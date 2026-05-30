package com.calibre.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.calibre.android.database.CalibreDatabase
import com.calibre.android.database.dao.*

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideCalibreDatabase(
        @ApplicationContext context: Context
    ): CalibreDatabase {
        return CalibreDatabase.getInstance(context)
    }
    
    @Provides
    fun provideBookDao(database: CalibreDatabase): BookDao = database.bookDao()
    
    @Provides
    fun provideReadingProgressDao(database: CalibreDatabase): ReadingProgressDao = database.readingProgressDao()
    
    @Provides
    fun provideCollectionDao(database: CalibreDatabase): CollectionDao = database.collectionDao()
    
    @Provides
    fun provideMetadataDao(database: CalibreDatabase): MetadataDao = database.metadataDao()
    
    @Provides
    fun provideDeviceSyncDao(database: CalibreDatabase): DeviceSyncDao = database.deviceSyncDao()
    
    @Provides
    fun provideConversionTaskDao(database: CalibreDatabase): ConversionTaskDao = database.conversionTaskDao()
    
    @Provides
    fun provideSettingDao(database: CalibreDatabase): SettingDao = database.settingDao()
}
