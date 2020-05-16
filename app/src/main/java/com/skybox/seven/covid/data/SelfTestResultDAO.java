package com.skybox.seven.covid.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
