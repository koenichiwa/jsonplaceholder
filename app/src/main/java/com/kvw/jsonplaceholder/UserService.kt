package com.kvw.jsonplaceholder

import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getAll(): List<User>
}