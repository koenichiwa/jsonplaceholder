package com.kvw.jsonplaceholder.business.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(@PrimaryKey val id: Int, val name: String, val username: String, val email: String) : Parcelable