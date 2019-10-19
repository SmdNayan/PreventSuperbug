package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Complain {
    @Expose
    @SerializedName("complainId")
    private Integer complainId;

    @Expose
    @SerializedName("providerName")
    private String providerName;

    @Expose
    @SerializedName("farmacyName")
    private String farmacyName;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("details")
    private String details;

    @Expose
    @SerializedName("prescribed")
    private boolean isPrescribed;

    @Expose
    @SerializedName("prescriptionImage")
    private String prescriptionImage;

    @Expose
    @SerializedName("userId")
    private int userId;

    @Expose
    @SerializedName("active")
    private boolean active;

    @Expose
    @SerializedName("createAt")
    private Date createAt;
    @SerializedName("user")
    private User user;


    public Integer getComplainId() {
        return complainId;
    }

    public void setComplainId(Integer complainId) {
        this.complainId = complainId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getFarmacyName() {
        return farmacyName;
    }

    public void setFarmacyName(String farmacyName) {
        this.farmacyName = farmacyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getPrescribed() {
        return isPrescribed;
    }

    public void setPrescribed(Boolean prescribed) {
        isPrescribed = prescribed;
    }

    public String getPrescriptionImage() {
        return prescriptionImage;
    }

    public void setPrescriptionImage(String prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String requireFields() {
        StringBuilder sb = new StringBuilder();
        if (getFarmacyName().isEmpty()) {
            sb.append("Farmacy Name is Required!\t");
        }
        if (getDetails().isEmpty()) {
            sb.append("Details required!\t");
        }
        if (getProviderName().isEmpty()) {
            sb.append("Provider Name is required!\t");
        }
        if (getAddress().isEmpty()) {
            sb.append("Address is required!\t");
        }
        if (getUserId() == 0) {
            sb.append("UserId is required!\t");
        }
        if (getComplainId() == 0) {
            sb.append("Complain is required!\t");
        }
        if (getPrescribed() && getPrescriptionImage().isEmpty()) {
            sb.append("Prescription Image is required!\t");
        }
        return sb.toString();
    }
}
