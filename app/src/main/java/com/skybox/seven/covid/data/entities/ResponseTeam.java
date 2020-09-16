package com.skybox.seven.covid.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTeam {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
