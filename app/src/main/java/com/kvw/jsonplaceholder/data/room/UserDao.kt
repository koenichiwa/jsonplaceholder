package com.kvw.jsonplaceholder.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.room.entity.UserEntity

@Dao
abstract class UserDao {

    @Transaction
    open suspend fun getAll(): List<User> {
        return getAllEntities().map { User(it.id, it.name, it.username, it.email) }
    }

    @Query("SELECT * FROM UserEntity")
    protected abstract suspend fun getAllEntities(): List<UserEntity>

    @Transaction
    open suspend fun insert(users: Collection<User>) {
        insertUserEntities(users.map { UserEntity(it.id, it.name, it.username, it.name) })
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insertUserEntities(users: Collection<UserEntity>)
}
