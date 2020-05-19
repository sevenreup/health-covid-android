package com.skybox.seven.covid.data.entities;

import java.util.List;

public class InfoGraphic {
    private String title;
    private List<String> images;

    public InfoGraphic(String title, List<String> images) {
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
