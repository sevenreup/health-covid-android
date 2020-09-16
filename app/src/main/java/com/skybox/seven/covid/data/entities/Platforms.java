package com.skybox.seven.covid.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Platforms {

    @SerializedName("whatsAppChatbot")
    @Expose
    private String whatsAppChatbot;
    @SerializedName("tnm")
    @Expose
    private String tnm;
    @SerializedName("airtel")
    @Expose
    private String airtel;

    public String getWhatsAppChatbot() {
        return whatsAppChatbot;
    }

    public void setWhatsAppChatbot(String whatsAppChatbot) {
        this.whatsAppChatbot = whatsAppChatbot;
    }

    public String getTnm() {
        return tnm;
    }

    public void setTnm(String tnm) {
        this.tnm = tnm;
    }

    public String getAirtel() {
        return airtel;
    }

    public void setAirtel(String airtel) {
        this.airtel = airtel;
    }

}
