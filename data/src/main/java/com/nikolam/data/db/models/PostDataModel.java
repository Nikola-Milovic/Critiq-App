package com.nikolam.data.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.data.db.converters.Converters;
import com.nikolam.data.models.CommentNetworkModel;

import org.w3c.dom.Comment;

import java.util.List;


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

    @ColumnInfo(name = "postedOnMillis")
    public Long datePostedOnMillis;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "comments")
    public List<CommentNetworkModel> comments;

    public PostDataModel(String awsImageLink, String comment, List<String> tags, String objectID, String awsThumbnailLink, Long datePostedOnMillis, List<CommentNetworkModel> comments) {
        this.awsImageLink = awsImageLink;
        this.comment = comment;
        this.tags = tags;
        this.objectID = objectID;
        this.awsThumbnailLink = awsThumbnailLink;
        this.datePostedOnMillis = datePostedOnMillis;
        this.comments = comments;
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

    public Long getDatePostedOnMillis() {
        return datePostedOnMillis;
    }

    public List<CommentNetworkModel> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "PostDataModel{" +
                "uid=" + uid +
                ", awsImageLink='" + awsImageLink + '\'' +
                ", comment='" + comment + '\'' +
                ", tags=" + tags +
                ", objectID='" + objectID + '\'' +
                ", awsThumbnailLink='" + awsThumbnailLink + '\'' +
                ", datePostedOnMillis=" + datePostedOnMillis +
                ", comments=" + comments +
                '}';
    }
}