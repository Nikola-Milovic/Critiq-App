package com.nikolam.feature_auth.data.models;

public class RegistrationResponse {

    private String token;
    private String permalink;
    private String id;

    public RegistrationResponse(String token, String permalink, String id) {
        this.token = token;
        this.permalink = permalink;
        this.id = id;
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

    public String getId() {
        return id;
    }
}
