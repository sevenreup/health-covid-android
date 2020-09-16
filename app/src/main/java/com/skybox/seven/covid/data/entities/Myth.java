package com.skybox.seven.covid.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = Myth.tableName)
public class Myth implements Serializable {
    final static String tableName = "myths";
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String myth;
    private String paragraph;
    private Integer locale;

    @Ignore
    public Myth(String title, String myth, String paragraph) {
        super();
        this.title = title;
        this.myth = myth;
        this.paragraph = paragraph;
    }

    @Ignore
    public Myth(Integer id, Integer locale, String title, String myth, String paragraph) {
        this.id = id;
        this.title = title;
        this.myth = myth;
        this.paragraph = paragraph;
        this.locale = locale;
    }

    public Myth() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMyth() {
        return myth;
    }

    public void setMyth(String myth) {
        this.myth = myth;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public Integer getLocale() {
        return locale;
    }

    public void setLocale(Integer locale) {
        this.locale = locale;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
