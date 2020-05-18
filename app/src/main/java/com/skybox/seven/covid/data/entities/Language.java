package com.skybox.seven.covid.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "languages")
public class Language {
    @PrimaryKey(autoGenerate = true)
    private Integer languageId;
    private String language;
    private String country;

    public Language() {
    }
    public Language(String language, String country) {
        super();
        this.language = language;
        this.country = country;
    }
    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }



    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
