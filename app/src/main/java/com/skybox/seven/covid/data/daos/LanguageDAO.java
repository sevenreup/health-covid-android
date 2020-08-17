package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.Language;

import java.util.List;

@Dao
public interface LanguageDAO {
    @Query("SELECT * From languages")
    List<Language> getAllLanguages();

    @Insert
    void insertAll(List<Language> languages);
}
