package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ComplainImage {

    @Expose
    @SerializedName("complainImageId")
    private Integer complainImageId;

    @Expose
    @SerializedName("imageTitle")
    private String imageTitle;

    @Expose
    @SerializedName("complainId")
    private String complainId;

    @Expose
    @SerializedName("active")
    private Boolean active;

    @Expose
    @SerializedName("create_at")
    private Boolean createAt;

    public Integer getComplainImageId() {
        return complainImageId;
    }

    public void setComplainImageId(Integer complainImageId) {
        this.complainImageId = complainImageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Boolean createAt) {
        this.createAt = createAt;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }
}
