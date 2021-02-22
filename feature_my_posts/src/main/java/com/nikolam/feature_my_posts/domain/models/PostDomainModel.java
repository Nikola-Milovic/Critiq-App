package com.nikolam.feature_my_posts.domain.models;

import com.nikolam.data.db.models.PostDataModel;

import java.util.List;

public class PostDomainModel {
    private int uid;
    private String awsImageLink;
    private String comment;
    private List<String> tags;
    private String objectID;
    private String awsThumbnailLink;

    public PostDomainModel(String awsImageLink, String comment, List<String> tags, String objectID, int uid, String thumbnailLink) {
        this.awsImageLink = awsImageLink;
        this.comment = comment;
        this.tags = tags;
        this.objectID = objectID;
        this.uid = uid;
        this.awsThumbnailLink = thumbnailLink;
    }

    public static PostDomainModel dataToDomainModel(PostDataModel model) {
        return new PostDomainModel(model.getAwsImageLink(), model.getComment(), model.getTags(), model.getObjectID(), model.getUid(), model.getAwsThumbnailLink());
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

    @Override
    public String toString() {
        return "PostDomainModel{" +
                ", awsImageLink='" + awsImageLink + '\'' +
                ", comment='" + comment + '\'' +
                ", tags=" + tags +
                ", objectID='" + objectID + '\'' +
                ", awsThumbnailLink='" + awsThumbnailLink + '\'' +
                '}';
    }
}