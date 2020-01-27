package com.kvw.jsonplaceholder.data.retrofit

import com.kvw.jsonplaceholder.business.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    suspend fun getByAlbumId(@Query("albumId") albumId: Int): List<Photo>
}
