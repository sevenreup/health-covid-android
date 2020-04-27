package com.skybox.seven.covid.ui.adapters;

public class ContactRequestModel {
    private String name;
    private String phone;

    public ContactRequestModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public ContactRequestModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
