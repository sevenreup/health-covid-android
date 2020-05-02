package com.skybox.seven.covid.network.responses;

import com.google.gson.annotations.SerializedName;

public class ContactRequest {
    @SerializedName("recipientName")
    public String name;
    @SerializedName("recipientNumber")
    public String phoneNumber;
    public String latitude;
    public String longitude;

    public ContactRequest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
