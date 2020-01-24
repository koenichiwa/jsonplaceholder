package com.kvw.jsonplaceholder.data.retrofit

import com.kvw.jsonplaceholder.business.model.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getAll(): List<User>
}
