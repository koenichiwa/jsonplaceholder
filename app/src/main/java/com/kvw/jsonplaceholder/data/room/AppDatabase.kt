package com.kvw.jsonplaceholder.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kvw.jsonplaceholder.business.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}