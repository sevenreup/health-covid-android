package com.skybox.seven.covid.data;

import androidx.room.Entity;

@Entity(tableName = Advice.tableName)
public class Advice {
    final static String tableName = "advices";
    private String shortTitle;
    private String title;
    private String advice;
    private String video;
    private int image;
    private boolean hasVideo;
    private boolean expanded = false;

    public Advice(String shortTitle, String title, String advice, int image) {
        this.shortTitle = shortTitle;
        this.title = title;
        this.advice = advice;
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

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
