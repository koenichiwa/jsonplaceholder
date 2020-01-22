package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.Album
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.retrofit.AlbumService
import com.kvw.jsonplaceholder.data.room.AlbumDao
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AlbumRepository {
    fun getByUser(user: User) : Flow<Intel<List<Album>>>
}

class AlbumRepositoryDefault(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
): AlbumRepository {
    override fun getByUser(user: User): Flow<Intel<List<Album>>> = flow {
        emit(Intel.Pending())
        var localList = emptyList<Album>()

        try {
            albumDao.getByUser(user).let {
                if(!it.isNullOrEmpty()){
                    localList = it
                    emit(Intel.Success(Intel.Source.Local, localList))
                }
            }

        } catch (t : Throwable){
            emit(Intel.Error(Intel.Source.Local, t, "Something went wrong while fetching from local"))
        }

        try {
            albumService.getByUserId(user.id).let {
                if (it != localList) {
                    emit(Intel.Success(Intel.Source.Remote, it))
                    albumDao.insert(user, it)
                }
            }
        } catch(t : Throwable){
            emit(Intel.Error(Intel.Source.Remote, t, "Something went wrong while fetching from remote"))
        }
    }
}