package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Article {
    @SerializedName("articleId")
    @Expose
    private int articleId;

    @SerializedName("articleTitle")
    @Expose
    private String articleTitle;

    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;

    @SerializedName("longDesc")
    @Expose
    private String longDesc;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("createAt")
    @Expose
    private Date createAt;

    @SerializedName("active")
    @Expose
    private int active;

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @SerializedName("thumbImageTitle")
    @Expose
    private String thumbImageTitle;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getThumbImageTitle() {
        return thumbImageTitle;
    }

    public void setThumbImageTitle(String thumbImageTitle) {
        this.thumbImageTitle = thumbImageTitle;
    }
}