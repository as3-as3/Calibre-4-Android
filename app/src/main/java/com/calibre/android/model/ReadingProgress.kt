package com.calibre.android.model

data class ReadingProgress(
    val bookId: String,
    val currentPage: Int,
    val totalPages: Int,
    val progressPercent: Float,
    val lastReadDate: Long,
    val status: String
)
