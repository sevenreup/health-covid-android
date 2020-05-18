package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Query;

import com.skybox.seven.covid.data.resultentities.MythWithLanguages;

import java.util.List;

@Dao
public interface MythsDAO {
    @Query("SELECT * From languages")
    public List<MythWithLanguages> getAllLanguagesMyth();
}
