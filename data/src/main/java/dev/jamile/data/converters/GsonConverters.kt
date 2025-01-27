package dev.jamile.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringList(value: String?): List<String>? =
        value?.let {
            val listType = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(it, listType)
        }

    @TypeConverter
    fun toStringList(list: List<String>?): String? = list?.let { Gson().toJson(it) }
}
