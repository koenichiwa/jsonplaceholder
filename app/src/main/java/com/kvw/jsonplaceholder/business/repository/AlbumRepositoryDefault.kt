package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.util.RepositoryRequest
import com.kvw.jsonplaceholder.data.retrofit.AlbumService
import com.kvw.jsonplaceholder.data.room.AlbumDao

class AlbumRepositoryDefault(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
) : AlbumRepository {
    override fun getByUser(user: User) =
        RepositoryRequest(
            fetch = { albumService.getByUserId(user.id) },
            read = { albumDao.getByUser(user) },
            write = { albumDao.insert(user, it) }
        ).fromLocalFirst()
}
