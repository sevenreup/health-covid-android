package com.skybox.seven.covid.network.responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("access_token") private String token;
    @SerializedName("token_type") private String type;
    private String name;
    private String phone;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
