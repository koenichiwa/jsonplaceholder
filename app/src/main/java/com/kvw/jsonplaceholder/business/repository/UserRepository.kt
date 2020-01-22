package com.kvw.jsonplaceholder.business.repository

import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.retrofit.UserService
import com.kvw.jsonplaceholder.data.room.UserDao
import com.kvw.jsonplaceholder.util.Intel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    fun getAll() : Flow<Intel<List<User>>>
}

class UserRepositoryDefault(private val userService: UserService, private val userDao: UserDao) :
    UserRepository {
    override fun getAll(): Flow<Intel<List<User>>> = flow {
        emit(Intel.Pending())
        var localList = emptyList<User>()

        try {
            userDao.getAll().let {
                if(!it.isNullOrEmpty()){
                    localList = it
                    emit(Intel.Success(localList))
                }
            }

        } catch (t : Throwable){
            emit(Intel.Error(t, "Something went wrong while fetching from local"))
        }

        try {
            userService.getAll().let {
                if (it != localList) {
                    emit(Intel.Success(it))
                    userDao.insertUsers(*it.toTypedArray())
                }
            }
        } catch(t : Throwable){
            emit(Intel.Error(t, "Something went wrong while fetching from remote"))
        }
    }
}