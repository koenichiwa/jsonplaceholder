package com.kvw.jsonplaceholder.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.Photo
import com.kvw.jsonplaceholder.data.room.entity.PhotoEntity

@Dao
abstract class PhotoDao {
    @Transaction
    open suspend fun getByAlbum(album: Album): List<Photo> {
        return getPhotoEntitiesByAlbumId(album.id).map { Photo(it.id, it.title, it.url, it.thumnailUrl) }
    }

    @Query("SELECT * FROM PhotoEntity WHERE albumId = :albumId")
    protected abstract suspend fun getPhotoEntitiesByAlbumId(albumId: Int): List<PhotoEntity>

    @Transaction
    open suspend fun insert(album: Album, albums: Collection<Photo>) {
        insertPhotoEntities(albums.map { PhotoEntity(it.id, album.id, it.title, it.url, it.thumbnailUrl) })
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertPhotoEntities(photos: Collection<PhotoEntity>)
}
