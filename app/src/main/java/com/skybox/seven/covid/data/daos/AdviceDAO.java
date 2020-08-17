package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.Advice;

import java.util.List;

@Dao
public interface AdviceDAO {
    @Query("SELECT * From advices WHERE languageOwnerId = :language")
    List<Advice> getAllAdvices(Integer language);

    @Insert
    void insertAll(List<Advice> myths);
}
