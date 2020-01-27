package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.data.retrofit.PhotoService
import com.kvw.jsonplaceholder.data.room.dao.PhotoDao
import com.kvw.jsonplaceholder.util.RepositoryRequest

class PhotoRepositoryDefault(
    private val photoService: PhotoService,
    private val photoDao: PhotoDao
) : PhotoRepository {
    override fun getByAlbum(album: Album) = RepositoryRequest(
        fetch = { photoService.getByAlbumId(album.id) },
        read = { photoDao.getByAlbum(album) },
        write = { photoDao.insert(album, it) }
    ).fromLocalFirst()
}
