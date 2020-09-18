package com.skybox.seven.covid.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = Advice.tableName)
public class Advice implements Serializable {
    final static String tableName = "advices";
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String shortTitle;
    private String title;
    private String advice;
    private String video;
    private int image;
    private boolean hasVideo;
    private boolean expanded = false;
    private Integer locale;

    public Advice() {
    }

    @Ignore
    public Advice(Integer id, Integer locale, String shortTitle, String title, String advice, int image) {
        super();
        this.id = id;
        this.shortTitle = shortTitle;
        this.title = title;
        this.advice = advice;
        this.video = null;
        this.hasVideo = false;
        this.image = image;
        this.locale = locale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLocale() {
        return locale;
    }

    public void setLocale(Integer locale) {
        this.locale = locale;
    }

    @NonNull
    @Override
    public String toString() {
        return shortTitle;
    }
}
