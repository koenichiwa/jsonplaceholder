package com.kvw.jsonplaceholder.business.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(val id: Int, val title: String, val url: Uri, val thumbnailUrl: Uri) : Parcelable
