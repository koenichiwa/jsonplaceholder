package com.kvw.jsonplaceholder.data.room.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    // @ForeignKey(entity = AlbumEntity::class, parentColumns = ["id"], childColumns = ["albumId"])
    val albumId: Int,
    val title: String,
    val url: Uri,
    val thumnailUrl: Uri
)
