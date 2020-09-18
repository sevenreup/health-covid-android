package com.skybox.seven.covid.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactDetail {

    @SerializedName("helpline")
    @Expose
    private String helpline;
    @SerializedName("platforms")
    @Expose
    private Platforms platforms;
    @SerializedName("responseTeams")
    @Expose
    private List<ResponseTeam> responseTeams = null;

    public String getHelpline() {
        return helpline;
    }

    public void setHelpline(String helpline) {
        this.helpline = helpline;
    }

    public Platforms getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Platforms platforms) {
        this.platforms = platforms;
    }

    public List<ResponseTeam> getResponseTeams() {
        return responseTeams;
    }

    public void setResponseTeams(List<ResponseTeam> responseTeams) {
        this.responseTeams = responseTeams;
    }

}
