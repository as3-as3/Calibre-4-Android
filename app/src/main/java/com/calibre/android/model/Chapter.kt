package com.calibre.android.model

data class Chapter(
    val title: String,
    val href: String?,
    val chapterIndex: Int,
    val subChapters: List<Chapter> = emptyList()
)
