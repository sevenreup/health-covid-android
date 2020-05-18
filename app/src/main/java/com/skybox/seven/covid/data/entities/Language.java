package com.skybox.seven.covid.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Language.tableName)
public class Language {
    final static String tableName = "languages";
    @PrimaryKey(autoGenerate = true)
    private Integer languageId;
    private String language;
    private String country;

    public Language() {
    }

    @Ignore
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
