package com.skybox.seven.covid.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class MenuItem {
    private int location;
    @DrawableRes
    private int image;
    @StringRes
    private int title;
    private int id;

    public MenuItem(int image, int title, int location, int id) {
        this.location = location;
        this.image = image;
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
