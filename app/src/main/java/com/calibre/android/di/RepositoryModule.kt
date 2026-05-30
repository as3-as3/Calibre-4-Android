package com.calibre.android.di

import com.calibre.android.repository.BookRepository
import com.calibre.android.repository.IBookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepository: BookRepository
    ): IBookRepository
}
