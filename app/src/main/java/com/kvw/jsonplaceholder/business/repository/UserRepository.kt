package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.util.Intel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAll(): Flow<Intel<List<User>>>
}
