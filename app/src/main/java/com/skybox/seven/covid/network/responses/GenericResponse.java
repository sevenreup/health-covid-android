package com.skybox.seven.covid.network.responses;

import com.google.gson.annotations.SerializedName;

public class GenericResponse {
    @SerializedName("message") private String message;
    @SerializedName("status") private boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
