package com.calibre.android.model

import com.calibre.android.database.entity.CollectionEntity

data class Collection(
    val id: String,
    val name: String,
    val description: String?,
    val color: String,
    val icon: String,
    val bookCount: Int
)

fun CollectionEntity.toModel() = Collection(
    id = id,
    name = name,
    description = description,
    color = color,
    icon = icon,
    bookCount = bookCount
)
