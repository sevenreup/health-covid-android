package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.resultentities.MythWithLanguages;

import java.util.List;

@Dao
public interface MythsDAO {
    @Query("SELECT * From languages")
    List<MythWithLanguages> getAllLanguagesMyth();

    @Insert
    void insertAll(List<Myth> myths);

    @Insert
    void insert(Myth myth);
}
