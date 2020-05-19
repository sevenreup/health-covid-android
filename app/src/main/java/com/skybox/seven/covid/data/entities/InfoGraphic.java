package com.skybox.seven.covid.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = InfoGraphic.tableName)
public class InfoGraphic {
    final static String tableName = "infoGraphics";
    @PrimaryKey
    private Integer id;
    private String image;
    private Integer type;

    public InfoGraphic() {
    }

    @Ignore
    public InfoGraphic(Integer id, String image, Integer type) {
        this.id = id;
        this.image = image;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
