package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.retrofit.UserService
import com.kvw.jsonplaceholder.data.room.UserDao
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAll() : Flow<Intel<List<User>>>
}

class UserRepositoryDefault(private val userService: UserService, private val userDao: UserDao) :
    UserRepository {
    override fun getAll(): Flow<Intel<List<User>>> =
        RepositoryRequest(
            fetch = userService::getAll,
            read = userDao::getAll,
            write = userDao::insert
        ).fromLocalFirst()
}