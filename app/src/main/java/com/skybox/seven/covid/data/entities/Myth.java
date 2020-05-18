package com.skybox.seven.covid.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Myth.tableName)
public class Myth {
    final static String tableName = "myths";
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String myth;
    private String paragraph;
    private Integer languageOwnerId;

    public Myth(String title, String myth, String paragraph) {
        super();
        this.title = title;
        this.myth = myth;
        this.paragraph = paragraph;
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
