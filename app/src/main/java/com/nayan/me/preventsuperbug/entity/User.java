package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

public class User {
    @SerializedName("userId")
    @Expose
    private long userId;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("password")
    @Expose(serialize = false)
    private String password;

    @SerializedName("hospital")
    @Expose
    private String hospital;

    @SerializedName("speciality")
    @Expose(serialize = false)
    private String speciality;

    @SerializedName("qualification")
    @Expose
    private String qualification;


    @SerializedName("active")
    @Expose
    private int active;

//    @Column(name = "create_at")
//    @SerializedName("createAt")
//    @Expose
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
//    private Date create_at;

    @Expose(deserialize = false)
    private Set<Role> roles;

    @SerializedName("complains")
    private List<Complain> complains;

    public int getUserId() {
        return (int) userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
//
//    public Date getCreate_at() {
//        return create_at;
//    }
//
//    public void setCreate_at(Date create_at) {
//        this.create_at = create_at;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String requireFields() {
        StringBuilder sb = new StringBuilder();
        if (getFullName().isEmpty()) {
            sb.append("User Full Name is Required!\t");
        }
        if (getEmail().isEmpty()) {
            sb.append("Email is required!\t");
        }
        if (getPassword().isEmpty()) {
            sb.append("Password is required!\t");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "--------------------\n" + fullName + "\n" + hospital + "\n" + speciality + "\n" + qualification;
    }
}
