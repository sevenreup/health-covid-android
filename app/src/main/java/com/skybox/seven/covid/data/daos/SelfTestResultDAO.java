package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.SelfTestResult;

import java.util.List;

@Dao
public interface SelfTestResultDAO {
    String TABLE_NAME = "Results";

    @Query("SELECT * FROM " + TABLE_NAME)
    List<SelfTestResult> getAll();

    @Insert
    void insertAll(SelfTestResult... test);

    @Delete
    void delete(SelfTestResult test);
}
