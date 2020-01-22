package com.kvw.jsonplaceholder.business.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(val id: Int, /*user: User,*/ val title: String): Parcelable