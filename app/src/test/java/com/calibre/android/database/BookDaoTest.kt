package com.calibre.android.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.calibre.android.database.entity.BookEntity
import com.calibre.android.database.dao.BookDao

@RunWith(AndroidJUnit4::class)
class BookDaoTest {
    
    private lateinit var database: CalibreDatabase
    private lateinit var bookDao: BookDao
    
    @Before
    fun setup() {
        // This requires Robolectric to run in 'test' folder
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CalibreDatabase::class.java
        ).allowMainThreadQueries().build()
        bookDao = database.bookDao()
    }
    
    @After
    fun tearDown() {
        database.close()
    }
    
    @Test
    fun testInsertAndRetrieveBook() = runTest {
        val book = BookEntity(
            title = "Test Book",
            author = "Test Author",
            format = "EPUB",
            filePath = "/path/to/book.epub",
            fileSize = 1024L,
            isbn = null,
            publisher = null,
            publishDate = null,
            series = null
        )
        
        bookDao.insert(book)
        val retrieved = bookDao.getById(book.id).first()
        
        assert(retrieved?.title == "Test Book")
        assert(retrieved?.author == "Test Author")
    }
}
