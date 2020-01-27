package com.kvw.jsonplaceholder.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kvw.jsonplaceholder.data.room.dao.AlbumDao
import com.kvw.jsonplaceholder.data.room.dao.PhotoDao
import com.kvw.jsonplaceholder.data.room.dao.UserDao
import com.kvw.jsonplaceholder.data.room.entity.AlbumEntity
import com.kvw.jsonplaceholder.data.room.entity.PhotoEntity
import com.kvw.jsonplaceholder.data.room.entity.UserEntity

@Database(entities = [UserEntity::class, AlbumEntity::class, PhotoEntity::class], version = 6, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao
}
