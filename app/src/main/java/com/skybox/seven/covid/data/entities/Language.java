package com.skybox.seven.covid.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Language.tableName)
public class Language {
    final static String tableName = "languages";
    @PrimaryKey
    private Integer languageId;
    private String language;
    private String country;
    private String name;

    public Language() {
    }

    @Ignore
    public Language(Integer id, String language, String country, String name) {
        super();
        this.languageId = id;
        this.language = language;
        this.country = country;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
