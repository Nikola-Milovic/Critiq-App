package com.nikolam.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nikolam.data.db.models.PostDataModel;


@Database(entities = {PostDataModel.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {
    abstract PostDao postDao();
}