package com.skybox.seven.covid.model;

import com.google.gson.annotations.SerializedName;

public class PreventionModel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String Title;

    @SerializedName("description")
    private String Description;

    public PreventionModel(Integer id, String title, String description) {
        this.id = id;
        Title = title;
        Description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
