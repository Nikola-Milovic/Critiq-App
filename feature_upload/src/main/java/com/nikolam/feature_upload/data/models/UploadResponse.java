package com.nikolam.feature_upload.data.models;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("aws_image_link")
    private final String awsImageLink;

    @SerializedName("id")
    private final String postID;

    @SerializedName("thumbnail")
    private final String awsThumbnailLink;

    public UploadResponse(String awsImageLink, String postID, String awsThumbnailLink) {
        this.awsImageLink = awsImageLink;
        this.postID = postID;
        this.awsThumbnailLink = awsThumbnailLink;
    }

    public String getAwsImageLink() {
        return awsImageLink;
    }

    public String getPostID() {
        return postID;
    }

    public String getAwsThumbnailLink() {
        return awsThumbnailLink;
    }
}

