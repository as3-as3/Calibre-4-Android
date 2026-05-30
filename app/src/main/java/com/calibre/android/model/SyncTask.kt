package com.calibre.android.model

data class SyncTask(
    val id: String,
    val bookId: String,
    val deviceId: String,
    val status: String,
    val progress: Int = 0,
    val error: String? = null
)
