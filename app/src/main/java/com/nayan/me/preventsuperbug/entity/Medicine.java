package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Set;


public class Medicine {
    @SerializedName("medicineId")
    @Expose
    private Integer medicineId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("groupId")
    @Expose
    private int groupId;

    @SerializedName("price")
    @Expose
    private float price;

    @SerializedName("companyId")
    @Expose
    private int companyId;

    @SerializedName("dosageForm")
    @Expose
    private String dosageForm;

    @SerializedName("strength")
    @Expose
    private String strength;


    @SerializedName("manufactureDate")
    @Expose
    private Date manufactureDate;

    @SerializedName("expiryDate")
    @Expose
    private Date expiryDate;

    @SerializedName("medicineGroup")
    @Expose
    private MedicineGroup medicineGroup;

    @SerializedName("medicineCompany")
    @Expose
    private MedicineCompany medicineCompany;

    @SerializedName("medicineImages")
    @Expose
    private Set<MedicineImage> medicineImages;

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String requireFields() {
        StringBuilder sb = new StringBuilder();
        if (getName().isEmpty()) {
            sb.append("Name is Required!\t");
        }
        if (getGroupId() == 0) {
            sb.append("Medicine Group is required!\t");
        }
        if (getPrice() == 0f) {
            sb.append("Price is required!\t");
        }
        if (getManufactureDate() == null) {
            sb.append("Manufacture Date is required!\t");
        }
        if (getExpiryDate() == null) {
            sb.append("Expiry Date is required!\t");
        }
        return sb.toString();
    }

    public MedicineGroup getMedicineGroup() {
        return medicineGroup;
    }

    public MedicineCompany getMedicineCompany() {
        return medicineCompany;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }
}
