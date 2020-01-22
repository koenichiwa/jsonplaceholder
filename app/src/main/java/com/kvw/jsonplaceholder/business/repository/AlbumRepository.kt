package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.retrofit.AlbumService
import com.kvw.jsonplaceholder.data.room.AlbumDao
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getByUser(user: User) : Flow<Intel<List<Album>>>
}

class AlbumRepositoryDefault(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
): AlbumRepository {
    override fun getByUser(user: User): Flow<Intel<List<Album>>> =
        RepositoryRequest(
            fetch = { albumService.getByUserId(user.id) },
            read = { albumDao.getByUser(user) },
            write = { albumDao.insert(user, it) }
        ).fromLocalFirst()
}