package com.skybox.seven.covid.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Myth.tableName)
public class Myth {
    final static String tableName = "myths";
    @PrimaryKey
    private Integer id;
    private String title;
    private String myth;
    private String paragraph;
    private Integer languageOwnerId;

    @Ignore
    public Myth(String title, String myth, String paragraph) {
        super();
        this.title = title;
        this.myth = myth;
        this.paragraph = paragraph;
    }

    @Ignore
    public Myth(Integer id, Integer languageOwnerId, String title, String myth, String paragraph) {
        this.id = id;
        this.title = title;
        this.myth = myth;
        this.paragraph = paragraph;
        this.languageOwnerId = languageOwnerId;
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

    public Integer getLanguageOwnerId() {
        return languageOwnerId;
    }

    public void setLanguageOwnerId(Integer languageOwnerId) {
        this.languageOwnerId = languageOwnerId;
    }
}
