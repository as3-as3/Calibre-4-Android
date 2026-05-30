package com.calibre.android.model

data class ConversionTask(
    val id: String,
    val bookId: String,
    val sourceFormat: String,
    val targetFormat: String,
    val status: String,
    val progressPercent: Int,
    val error: String?,
    val createdDate: Long,
    val completionDate: Long?
)
