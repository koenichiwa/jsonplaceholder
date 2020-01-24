package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.util.RepositoryRequest
import com.kvw.jsonplaceholder.data.retrofit.UserService
import com.kvw.jsonplaceholder.data.room.UserDao

class UserRepositoryDefault(private val userService: UserService, private val userDao: UserDao) :
    UserRepository {
    override fun getAll() =
        RepositoryRequest(
            fetch = userService::getAll,
            read = userDao::getAll,
            write = userDao::insert
        ).fromLocalFirst()
}
