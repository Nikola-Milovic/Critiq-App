package com.nikolam.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostNetworkModel {
    @SerializedName("user_id")
    private final String userID;

    @SerializedName("aws_image_link")
    private final String awsImageLink;

    @SerializedName("posted_on")
    private final Long datePostedInMillis;

    @SerializedName("comment")
    private final String comment;

    @SerializedName("tags")
    private final List<String> tags;

    @SerializedName("aws_key")
    private final String awsKey;

    @SerializedName("aws_thumbnail_image_link")
    private final String awsThumbnailLink;

    @SerializedName("aws_thumbnail_key")
    private final String awsThumbnailKey;

    @SerializedName("_id")
    private final String objectID;

    @SerializedName("comments")
    private final List<CommentNetworkModel> comments;

    public PostNetworkModel(String userID, String awsImageLink, Long datePostedInMillis, String comment, List<String> tags, String awsKey, String awsThumbnailLink, String awsThumbnailKey, String objectID, List<CommentNetworkModel> comments) {
        this.userID = userID;
        this.awsImageLink = awsImageLink;
        this.datePostedInMillis = datePostedInMillis;
        this.comment = comment;
        this.tags = tags;
        this.awsKey = awsKey;
        this.awsThumbnailLink = awsThumbnailLink;
        this.awsThumbnailKey = awsThumbnailKey;
        this.objectID = objectID;
        this.comments = comments;
    }

    public String getUserID() {
        return userID;
    }

    public String getAwsImageLink() {
        return awsImageLink;
    }

    public Long getDatePostedInMillis() {
        return datePostedInMillis;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getAwsKey() {
        return awsKey;
    }

    public String getAwsThumbnailLink() {
        return awsThumbnailLink;
    }

    public String getObjectID() {
        return objectID;
    }

    public List<CommentNetworkModel> getComments() {
        return comments;
    }
}
