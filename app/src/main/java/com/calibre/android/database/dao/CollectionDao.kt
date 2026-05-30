package com.calibre.android.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.calibre.android.database.entity.CollectionEntity
import com.calibre.android.database.entity.BookCollectionEntity
import com.calibre.android.database.entity.BookEntity

@Dao
interface CollectionDao {
    
    @Insert
    suspend fun insert(collection: CollectionEntity): Long
    
    @Update
    suspend fun update(collection: CollectionEntity): Int
    
    @Delete
    suspend fun delete(collection: CollectionEntity): Int
    
    @Query("SELECT * FROM collections ORDER BY name ASC")
    fun getAllCollections(): Flow<List<CollectionEntity>>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookToCollection(bookCollection: BookCollectionEntity)
    
    @Delete
    suspend fun removeBookFromCollection(bookCollection: BookCollectionEntity)
    
    @Query("""
        SELECT b.* FROM books b
        INNER JOIN book_collections bc ON b.id = bc.bookId
        WHERE bc.collectionId = :collectionId
        ORDER BY b.title ASC
    """)
    fun getBooksInCollection(collectionId: String): Flow<List<BookEntity>>
}
