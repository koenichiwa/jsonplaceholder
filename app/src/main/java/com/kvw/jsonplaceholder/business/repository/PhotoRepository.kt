package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.Photo
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getByAlbum(album: Album): Flow<Intel<List<Photo>>>
}
