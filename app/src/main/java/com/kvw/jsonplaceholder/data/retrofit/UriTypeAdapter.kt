package com.kvw.jsonplaceholder.data.retrofit

import android.net.Uri
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class UriTypeAdapter : JsonDeserializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        if (json == null || typeOfT == null || context == null)
            throw JsonParseException("Can't parse the Uri")
        return Uri.parse(json.asString)
    }
}
