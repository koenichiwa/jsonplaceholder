package com.kvw.jsonplaceholder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    fun getAll() : Flow<List<User>>
}

class UserRepositoryDefault(private val userService: UserService) : UserRepository{
    override fun getAll(): Flow<List<User>> = flow { emit(userService.getAll()) }

}