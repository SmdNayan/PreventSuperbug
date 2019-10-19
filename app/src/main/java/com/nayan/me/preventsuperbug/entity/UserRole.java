package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRole {
    @SerializedName("userRoleId")
    @Expose
    private long userRoleId;

    @SerializedName("roleId")
    @Expose
    private int roleId;
    @SerializedName("useId")
    @Expose
    private int userId;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
