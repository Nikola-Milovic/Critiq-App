package com.nikolam.common.models;

import com.google.gson.annotations.SerializedName;

public class CommentDomainModel {
    private final String userID;
    private final String contents;
    private final String objectID;

    public CommentDomainModel(String userID, String contents, String objectID) {
        this.userID = userID;
        this.contents = contents;
        this.objectID = objectID;
    }

    public String getUserID() {
        return userID;
    }

    public String getContents() {
        return contents;
    }

    public String getObjectID() {
        return objectID;
    }
}
