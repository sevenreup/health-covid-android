package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.resultentities.MythWithLanguages;

import java.util.List;

@Dao
public interface MythsDAO {
    @Transaction
    @Query("SELECT * From languages")
    List<MythWithLanguages> getAllLanguagesMyth();

    @Insert
    void insertAll(List<Myth> myths);

    @Insert
    void insert(Myth myth);
}
