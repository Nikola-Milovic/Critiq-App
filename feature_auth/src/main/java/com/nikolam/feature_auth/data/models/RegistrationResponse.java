package com.nikolam.feature_auth.data.models;

public class RegistrationResponse {

    private String token;
    private String permalink;

    public RegistrationResponse(String token, String permalink) {
        this.token = token;
        this.permalink = permalink;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
