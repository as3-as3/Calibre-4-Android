package com.calibre.android.model

data class Device(
    val id: String,
    val name: String,
    val type: String,
    val connectionType: String,
    val isConnected: Boolean,
    val storageAvailable: Long,
    val supportedFormats: List<String>
)
