package com.skybox.seven.covid.ui.adapters;

import com.google.gson.annotations.SerializedName;

public class ContactModel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("first_name")
    private String FName;

    @SerializedName("last_name")
    private String LName;

    @SerializedName("phone")
    private String phone;

    public ContactModel(Integer id, String FName, String LName, String phone) {
        this.id =id;
        this.FName = FName;
        this.LName = LName;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
