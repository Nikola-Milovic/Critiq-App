package com.nikolam.common.models;

import java.util.ArrayList;
import java.util.List;

public class PostDomainModel {

    public String awsImageLink;
    public String comment;
    public List<String> tags;
    public String objectID;
    public String userID;
    public String thumbnailLink;
    public Long postedOnMillis;
    public ArrayList<CommentDomainModel> comments;


    public PostDomainModel(String awsImageLink, String comment, List<String> tags, String objectID, String userID, String thumbnailLink, Long postedOnMillis, ArrayList<CommentDomainModel> comments) {
        this.awsImageLink = awsImageLink;
        this.comment = comment;
        this.tags = tags;
        this.objectID = objectID;
        this.userID = userID;
        this.thumbnailLink = thumbnailLink;
        this.postedOnMillis = postedOnMillis;
        this.comments = comments;
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

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<CommentDomainModel> getComments() {
        return comments;
    }


    public void setComments(ArrayList<CommentDomainModel> comments) {
        this.comments = comments;
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
                ", comments=" + comments +
                '}';
    }
}