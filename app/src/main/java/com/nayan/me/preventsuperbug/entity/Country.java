package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Country {
    @Expose
    @SerializedName("countryId")
    private long countryId;
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("code")
    private String code;

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
