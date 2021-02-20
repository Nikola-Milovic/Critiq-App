package com.nikolam.feature_upload.data.models;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("aws_image_link")
    private final String awsImageLink;

    @SerializedName("id")
    private final String postID;

    public UploadResponse(String awsImageLink, String postID) {
        this.awsImageLink = awsImageLink;
        this.postID = postID;
    }

    public String getAwsImageLink() {
        return awsImageLink;
    }

    public String getPostID() {
        return postID;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "awsImageLink='" + awsImageLink + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}

