package com.skybox.seven.covid.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidationErrors {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private Errors errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidationErrors withMessage(String message) {
        this.message = message;
        return this;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public ValidationErrors withErrors(Errors errors) {
        this.errors = errors;
        return this;
    }

    public class Errors {

        @SerializedName("phone")
        @Expose
        private List<String> phone = null;
        @SerializedName("password")
        @Expose
        private List<String> password = null;
        @SerializedName("first_name")
        @Expose
        private List<String> firstName = null;
        @SerializedName("last_name")
        @Expose
        private List<String> lastName = null;

        public List<String> getPhone() {
            return phone;
        }

        public void setPhone(List<String> phone) {
            this.phone = phone;
        }

        public Errors withPhone(List<String> phone) {
            this.phone = phone;
            return this;
        }

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }

        public List<String> getFirstName() {
            return firstName;
        }

        public void setFirstName(List<String> firstName) {
            this.firstName = firstName;
        }

        public List<String> getLastName() {
            return lastName;
        }

        public void setLastName(List<String> lastName) {
            this.lastName = lastName;
        }

        public Errors withPassword(List<String> password) {
            this.password = password;
            return this;
        }

    }

}
