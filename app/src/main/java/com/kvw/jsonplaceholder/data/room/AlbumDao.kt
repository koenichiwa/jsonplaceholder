package com.kvw.jsonplaceholder.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.room.entity.AlbumEntity

@Dao
abstract class AlbumDao{

    @Transaction
    open suspend fun getByUser(user: User): List<Album>{
        return getAlbumEntityByUserId(user.id).map { Album(it.id, it.title) }
    }

    @Query("SELECT * FROM AlbumEntity WHERE userId = :userId")
    protected abstract suspend fun getAlbumEntityByUserId(userId: Int): List<AlbumEntity>

    @Transaction
    open suspend fun insert(user: User, albums: Collection<Album>){
        insertAlbumEntities(albums.map { AlbumEntity(it.id, user.id, it.title) })
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAlbumEntities(albums: Collection<AlbumEntity>)
}