package com.skybox.seven.covid.model;

import java.util.List;

public class MythGraphicInfo {
    private String title;
    private List<String> images;

    public MythGraphicInfo(String title, List<String> images) {
        this.title = title;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
