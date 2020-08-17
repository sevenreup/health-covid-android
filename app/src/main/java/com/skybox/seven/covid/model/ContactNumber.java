package com.skybox.seven.covid.model;

public class ContactNumber {
    private String name, number, Location;
    private int type = RESPONSE_TEAM;

    public ContactNumber(String name, String number, int type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static final int  RESPONSE_TEAM = 0, HOSPITAL = 1, OTHERS = 2;
}
