package com.skybox.seven.covid.model;

public class Myth {
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
