package com.nikolam.data.models;

import com.google.gson.annotations.SerializedName;

public class CommentNetworkModel {
    @SerializedName("user_id")
    private final String userID;

    @SerializedName("contents")
    private final String contents;

    @SerializedName("_id")
    private final String objectID;

    public CommentNetworkModel(String userID, String contents, String objectID) {
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
