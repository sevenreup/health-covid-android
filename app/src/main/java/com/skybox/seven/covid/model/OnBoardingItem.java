package com.skybox.seven.covid.model;

public class OnBoardingItem {
    String title;
    String explanation;
    int image;
    int type;

    public OnBoardingItem(int type) {
        this.type = type;
    }

    public OnBoardingItem(String title, String explanation, int image, int type) {
        this.title = title;
        this.explanation = explanation;
        this.image = image;
        this.type = type;
    }

    public OnBoardingItem() {
    }

    public OnBoardingItem(String title, String explanation, int image) {
        this.title = title;
        this.explanation = explanation;
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static final int normal = 0, language = 1, end = 2, permissions = 3;
}
