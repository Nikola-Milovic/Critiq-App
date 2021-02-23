package com.nikolam.feature_post_detail.data.models;

public class PostCommentResponse {
    private final int status;

    public PostCommentResponse(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
