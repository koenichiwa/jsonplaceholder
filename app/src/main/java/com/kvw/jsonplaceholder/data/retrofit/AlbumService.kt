package com.kvw.jsonplaceholder.data.retrofit

import com.kvw.jsonplaceholder.business.model.Album
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    suspend fun getByUserId(@Query("userId") userId: Int): List<Album>
}