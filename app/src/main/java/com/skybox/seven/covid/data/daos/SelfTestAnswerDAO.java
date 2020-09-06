package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.SelfTestAnswer;

import java.util.List;

@Dao
public interface SelfTestAnswerDAO {
    String TABLE_NAME = "SelfTestAnswers";

    @Query("SELECT * FROM " + TABLE_NAME)
    List<SelfTestAnswer> getAll();

    @Insert
    void insertAll(SelfTestAnswer... test);

    @Delete
    void delete(SelfTestAnswer test);
}
