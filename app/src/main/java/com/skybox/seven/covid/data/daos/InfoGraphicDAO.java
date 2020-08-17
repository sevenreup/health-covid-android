package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.InfoGraphic;

import java.util.List;

@Dao
public interface InfoGraphicDAO {
    @Query("SELECT * From infoGraphics WHERE type = :type AND languageId = :locale")
    List<InfoGraphic> getAllInfoGraphics(Integer locale, Integer type);

    @Insert
    void insertAll(List<InfoGraphic> infoGraphics);
}
