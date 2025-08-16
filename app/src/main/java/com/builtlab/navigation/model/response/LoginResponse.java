package com.builtlab.navigation.model.response;

import com.builtlab.navigation.model.User;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName(value = "user")
    private User user;
    @SerializedName(value = "access_token")
    private String accessToken;
    @SerializedName(value = "refresh_token")
    private String refreshToken;

    public LoginResponse(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
