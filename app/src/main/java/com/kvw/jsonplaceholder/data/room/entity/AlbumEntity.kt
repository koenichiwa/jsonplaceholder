package com.kvw.jsonplaceholder.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String
)
