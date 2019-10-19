package com.nayan.me.preventsuperbug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nayan.me.preventsuperbug.entity.User;

import java.util.Date;

public class TokenResult {
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("expirationTime")
    private Date expirationTime;

    public TokenResult(String token, User user, Date expirationTime) {
        this.token = token;
        this.user = user;
        this.expirationTime = expirationTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
}
