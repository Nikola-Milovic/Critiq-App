package com.nikolam.data.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.nikolam.data.db.converters.Converters;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Converter;


@Entity(tableName = "myPosts")
public class PostDataModel {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "aws_image_link")
    public String awsImageLink;

    @ColumnInfo(name = "comment")
    public String comment;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "tags")
    public List<String> tags;

    @ColumnInfo(name = "object_id")
    public String objectID;

    @ColumnInfo(name = "thumbnail_link")
    public String awsThumbnailLink;

    public PostDataModel(String awsImageLink, String comment, List<String> tags, String objectID, String awsThumbnailLink) {
        this.awsImageLink = awsImageLink;
        this.comment = comment;
        this.tags = tags;
        this.objectID = objectID;
        this.awsThumbnailLink = awsThumbnailLink;
    }

    public int getUid() {
        return uid;
    }

    public String getAwsImageLink() {
        return awsImageLink;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public String getAwsThumbnailLink() {
        return awsThumbnailLink;
    }
}