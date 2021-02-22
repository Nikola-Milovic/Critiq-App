package com.nikolam.feature_post_detail.domain.models;

import com.nikolam.feature_post_detail.data.models.PostDataModel;

import java.util.List;

public class PostDomainModel {

    public String awsImageLink;
    public String comment;
    public List<String> tags;
    public String objectID;
    public String userID;
    public String thumbnailLink;
    public Long postedOnMillis;

    public PostDomainModel(String awsImageLink, String comment, List<String> tags, String objectID, String thumbnailLink, String userID, Long postedOnMillis) {
        this.awsImageLink = awsImageLink;
        this.comment = comment;
        this.tags = tags;
        this.objectID = objectID;
        this.thumbnailLink = thumbnailLink;
        this.userID = userID;
        this.postedOnMillis = postedOnMillis;
    }

    public static PostDomainModel dataToDomainModel(PostDataModel model) {
        return new PostDomainModel(model.getAwsImageLink(), model.getComment(), model.getTags(), model.getObjectID(), model.getAwsThumbnailLink(), model.getUserID(), model.getDatePostedInMillis());
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

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "PostDomainModel{" +
                "awsImageLink='" + awsImageLink + '\'' +
                ", comment='" + comment + '\'' +
                ", tags=" + tags +
                ", objectID='" + objectID + '\'' +
                ", userID='" + userID + '\'' +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", postedOnMillis=" + postedOnMillis +
                '}';
    }
}