package com.skybox.seven.covid.model;

public class FamMember {

    private String famMemberID, name, phoneNumber;
    private String userID; //to whom they are attached
    boolean verified = false;

    public FamMember(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public FamMember() {
    }

    public String getFamMemberID() {
        return famMemberID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
