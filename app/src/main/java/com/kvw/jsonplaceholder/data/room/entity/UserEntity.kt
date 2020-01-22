package com.kvw.jsonplaceholder.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(@PrimaryKey val id: Int, val name: String, val username: String, val email: String)