package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.network

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException

class ItemTypeAdapterFactory : TypeAdapterFactory {

    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {

        val delegate = gson.getDelegateAdapter(this, type)
        val elementAdapter = gson.getAdapter(JsonElement::class.java)

        return object : TypeAdapter<T>() {

            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: T) {
                delegate.write(out, value)
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): T {
                var jsonElement = elementAdapter.read(`in`)
                if (jsonElement.isJsonObject) {
                    val jsonObject = jsonElement.asJsonObject
                    if (jsonObject.has("results") && jsonObject.get("results").isJsonArray) {
                        jsonElement = jsonObject.getAsJsonArray("results")
                    }
                }
                return delegate.fromJsonTree(jsonElement)
            }
        }.nullSafe()
    }
}