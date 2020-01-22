package com.kvw.jsonplaceholder.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.data.room.entity.AlbumEntity
import com.kvw.jsonplaceholder.data.room.entity.UserEntity

@Database(entities = [UserEntity::class, AlbumEntity::class], version = 4, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao
}