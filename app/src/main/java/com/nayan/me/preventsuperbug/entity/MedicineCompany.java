package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineCompany {
    @SerializedName("companyId")
    @Expose
    private long companyId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("countryId")
    @Expose
    private String countryId;


    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}
