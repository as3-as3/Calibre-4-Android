package com.calibre.android.di

import com.calibre.android.service.conversion.ConversionService
import com.calibre.android.service.conversion.IConversionService
import com.calibre.android.service.device.DeviceSyncService
import com.calibre.android.service.device.IDeviceSyncService
import com.calibre.android.service.library.ILibraryService
import com.calibre.android.service.library.LibraryService
import com.calibre.android.service.metadata.IMetadataService
import com.calibre.android.service.metadata.MetadataService
import com.calibre.android.service.settings.ISettingsService
import com.calibre.android.service.settings.SettingsService
import com.calibre.android.service.viewer.BookViewerService
import com.calibre.android.service.viewer.IBookViewerService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    
    @Binds
    @Singleton
    abstract fun bindLibraryService(service: LibraryService): ILibraryService
    
    @Binds
    @Singleton
    abstract fun bindBookViewerService(service: BookViewerService): IBookViewerService
    
    @Binds
    @Singleton
    abstract fun bindConversionService(service: ConversionService): IConversionService
    
    @Binds
    @Singleton
    abstract fun bindDeviceSyncService(service: DeviceSyncService): IDeviceSyncService
    
    @Binds
    @Singleton
    abstract fun bindMetadataService(service: MetadataService): IMetadataService
    
    @Binds
    @Singleton
    abstract fun bindSettingsService(service: SettingsService): ISettingsService
}
