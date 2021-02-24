package com.nikolam.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.nikolam.data.db.converters.Converters;
import com.nikolam.data.db.models.PostDataModel;
import com.nikolam.data.models.CommentNetworkModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
interface PostDao {
    @Query("SELECT * FROM myPosts")
    Observable<List<PostDataModel>> getMyPosts();

    @Query("SELECT * FROM myPosts WHERE object_id = :id")
    Maybe<PostDataModel> getPostByObjectID(String id);

    @TypeConverters(Converters.class)
    @Query("UPDATE myPosts SET comments = :comments WHERE object_id = :id")
    Completable updatePostComments(String id, List<CommentNetworkModel> comments);

    @Insert
    Completable insertPost(PostDataModel post);
}