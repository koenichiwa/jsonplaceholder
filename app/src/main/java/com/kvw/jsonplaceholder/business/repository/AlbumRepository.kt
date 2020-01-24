package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getByUser(user: User): Flow<Intel<List<Album>>>
}
