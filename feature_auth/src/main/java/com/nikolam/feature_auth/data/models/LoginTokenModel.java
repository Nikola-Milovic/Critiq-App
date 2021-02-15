package com.nikolam.feature_auth.data.models;

public class LoginTokenModel {
    //@SerializedName("productReference")
    private String token;
    private String permalink;

    public LoginTokenModel(String token, String permalink) {
        this.token = token;
        this.permalink = permalink;
    }

    public String getToken() {
        return token;
    }

    public String getPermalink() {
        return permalink;
    }
}
