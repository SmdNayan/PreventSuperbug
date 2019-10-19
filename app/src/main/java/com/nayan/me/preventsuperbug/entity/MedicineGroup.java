package com.nayan.me.preventsuperbug.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class MedicineGroup {
    @SerializedName("groupId")
    @Expose
    private Integer groupId;

    @SerializedName("groupName")
    @Expose
    private String groupName;

    @SerializedName("medicines")
    @Expose
    private Set<Medicine> medicines;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String requireFields() {
        StringBuilder sb = new StringBuilder();
        if (getGroupName().isEmpty()) {
            sb.append("Group Name is Required!\t");
        }
        return sb.toString();
    }
}
