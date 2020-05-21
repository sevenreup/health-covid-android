package com.skybox.seven.covid.model;

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




    public class ContactUsersContacts
    {
        @SerializedName("id")
        private Integer id;

        @SerializedName("sender")
        private Integer sender;

        @SerializedName("recipient")
        private Integer recipient;

        @SerializedName("status")
        private  String status;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private  String updated_at;

        @SerializedName("user")
        private ContactModel user;

        public ContactUsersContacts(Integer id, Integer sender, Integer recipient, String status, String created_at, String updated_at, ContactModel user) {
            this.id = id;
            this.sender = sender;
            this.recipient = recipient;
            this.status = status;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.user = user;
        }

        public ContactUsersContacts() {
        }

        public ContactModel getUser() {
            return user;
        }

        public void setUser(ContactModel user) {
            this.user = user;
        }
    }


}

