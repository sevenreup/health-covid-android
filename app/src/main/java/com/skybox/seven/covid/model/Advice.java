package com.skybox.seven.covid.model;

public class Advice {
    private String shortTitle;
    private String title;
    private String advice;
    private String answer;
    private String video;
    private int image;
    private boolean hasVideo;

    public Advice(String shortTitle, String title, String advice, String answer, int image) {
        this.shortTitle = shortTitle;
        this.title = title;
        this.advice = advice;
        this.answer = answer;
        this.video = null;
        this.hasVideo = false;
        this.image = image;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public enum CurrentChip {
        advice,
        infographic
    }
}
