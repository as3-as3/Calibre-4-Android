package com.calibre.android.model

import com.calibre.android.database.entity.BookEntity

data class Book(
    val id: String,
    val title: String,
    val author: String?,
    val series: String?,
    val seriesIndex: Float?,
    val isbn: String?,
    val publisher: String?,
    val publishDate: Long?,
    val language: String,
    val description: String?,
    val rating: Int,
    val tags: List<String>,
    val format: String,
    val filePath: String,
    val fileSize: Long,
    val coverPath: String?,
    val coverUrl: String?,
    val addedDate: Long,
    val lastReadDate: Long?,
    val isFavorite: Boolean,
    val isArchived: Boolean
)

fun BookEntity.toModel() = Book(
    id = id,
    title = title,
    author = author,
    series = series,
    seriesIndex = seriesIndex,
    isbn = isbn,
    publisher = publisher,
    publishDate = publishDate,
    language = language,
    description = description,
    rating = rating,
    tags = tags.split(",").filter { it.isNotBlank() },
    format = format,
    filePath = filePath,
    fileSize = fileSize,
    coverPath = coverPath,
    coverUrl = coverUrl,
    addedDate = addedDate,
    lastReadDate = lastReadDate,
    isFavorite = isFavorite,
    isArchived = isArchived
)

fun Book.toEntity() = BookEntity(
    id = id,
    title = title,
    author = author,
    series = series,
    seriesIndex = seriesIndex,
    isbn = isbn,
    publisher = publisher,
    publishDate = publishDate,
    language = language,
    description = description,
    rating = rating,
    tags = tags.joinToString(","),
    format = format,
    filePath = filePath,
    fileSize = fileSize,
    coverPath = coverPath,
    coverUrl = coverUrl,
    addedDate = addedDate,
    lastReadDate = lastReadDate,
    isFavorite = isFavorite,
    isArchived = isArchived
)
