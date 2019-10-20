package com.nayan.me.preventsuperbug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DoctorSearch {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("speciality")
    @Expose
    private String speciality;

    @SerializedName("qualification")
    @Expose
    private String qualification;

    @SerializedName("hospital")
    @Expose
    private String hospital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getNullFieldName() {
        StringBuilder sb = new StringBuilder();
        if (getName() != null && getName().isEmpty()) {
            sb.append("Name is Required!\t");
        }
        if (getSpeciality() != null && getSpeciality().isEmpty()) {
            sb.append("Speciality required!\t");
        }
        if (getQualification() != null && getQualification().isEmpty()) {
            sb.append("Qualification is required!\t");
        }
        if (getHospital() != null && getHospital().isEmpty()) {
            sb.append("Hospital is required!\t");
        }
        return sb.toString();

    }
}
