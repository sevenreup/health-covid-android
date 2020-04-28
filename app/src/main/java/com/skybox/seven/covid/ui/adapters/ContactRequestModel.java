package com.skybox.seven.covid.ui.adapters;

import com.google.gson.annotations.SerializedName;

public class ContactRequestModel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("first_name")
    private String FName;

    @SerializedName("last_name")
    private String LName;

    @SerializedName("phone")
    private String phone;

    public ContactRequestModel(Integer id, String FName, String LName, String phone) {
        this.id = id;
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


    public class PendingContacts{
        @SerializedName("id")
        private Integer id;

        @SerializedName("recipient")
        private Integer recipient;


        @SerializedName("user")
        private ContactRequestModel user;

        public PendingContacts(Integer id, Integer recipient, ContactRequestModel user) {
            this.id = id;
            this.recipient = recipient;
            this.user = user;
        }

        public PendingContacts() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRecipient() {
            return recipient;
        }

        public void setRecipient(Integer recipient) {
            this.recipient = recipient;
        }

        public ContactRequestModel getUser() {
            return user;
        }

        public void setUser(ContactRequestModel user) {
            this.user = user;
        }


    }


}
