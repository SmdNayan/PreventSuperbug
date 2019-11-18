package com.nayan.me.preventsuperbug.core;

import android.app.Application;

import com.nayan.me.preventsuperbug.data.TokenResult;
import com.nayan.me.preventsuperbug.entity.User;
import com.nayan.me.preventsuperbug.network.http.CiHttpClient;

public class PBSBApplication extends Application {
    private static PBSBApplication instance;
    private static TokenResult userToken;

    public static String getToken() {
        if (userToken == null)
            return "ey";
        return userToken.getToken();
    }

    public static boolean isLoggedIn() {
        if (userToken != null) {
            return !userToken.getToken().isEmpty();
        } else {
            return false;
        }
    }

    public static User getUser() {
        if (userToken != null) {
            return userToken.getUser();
        }
        return null;
    }

    public static void setUser(User user) {
        if (user != null) {
            userToken.setUser(user);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CiHttpClient.setCacheFile(this.getCacheDir());
    }

    public static void setToken(TokenResult token) {
        userToken = token;
    }

    public static PBSBApplication getInstance() {
        return instance;
    }

}
