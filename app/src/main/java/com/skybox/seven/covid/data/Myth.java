package com.skybox.seven.covid.data;

import androidx.room.Entity;

@Entity(tableName = Myth.tableName)
public class Myth {
    final static String tableName = "myths";
    private String title;
    private String myth;
    private String paragraph;

    public Myth(String title, String myth, String paragraph) {
        this.title = title;
        this.myth = myth;
        this.paragraph = paragraph;
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
}
