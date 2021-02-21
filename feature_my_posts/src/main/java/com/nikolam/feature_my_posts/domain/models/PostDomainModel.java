package com.nikolam.feature_my_posts.domain.models;

import com.nikolam.data.db.models.PostDataModel;

import java.util.List;

public class PostDomainModel {
    public int uid;
    public String awsImageLink;
    public String comment;
    public List<String> tags;
    public String objectID;

    public PostDomainModel(String awsImageLink, String comment, List<String> tags, String objectID, int uid) {
        this.awsImageLink = awsImageLink;
        this.comment = comment;
        this.tags = tags;
        this.objectID = objectID;
        this.uid = uid;
    }

    public static PostDomainModel dataToDomainModel(PostDataModel model) {
        return new PostDomainModel(model.getAwsImageLink(), model.getComment(), model.getTags(), model.getObjectID(), model.getUid());
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
}