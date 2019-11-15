package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Role {
    @SerializedName("roleId")
    @Expose
    private Long roleId;
    @SerializedName("name")
    @Expose
    private String name;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
