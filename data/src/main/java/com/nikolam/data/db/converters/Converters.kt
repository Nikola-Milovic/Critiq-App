package com.nikolam.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nikolam.data.db.models.PostDataModel

public class Converters {
    @TypeConverter
    fun fromArrayListPostModel(list: List<PostDataModel?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toArrayListPostModel(str: String): List<PostDataModel> {
        val gson = Gson()
        val myType = object : TypeToken<List<PostDataModel>>() {}.type
        return gson.fromJson<ArrayList<PostDataModel>>(str, myType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toArrayList(str: String): List<String> {
        val gson = Gson()
        val myType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson<ArrayList<String>>(str, myType)
    }
}