package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineImage {
    @SerializedName("medicineImageId")
    @Expose
    private long medicineImageId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("medicineId")
    @Expose
    private int medicineId;

    @SerializedName("active")
    @Expose
    private int active;

    @SerializedName("medicine")
    private Medicine medicine;

    public long getMedicineImageId() {
        return medicineImageId;
    }

    public void setMedicineImageId(long medicineImageId) {
        this.medicineImageId = medicineImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
