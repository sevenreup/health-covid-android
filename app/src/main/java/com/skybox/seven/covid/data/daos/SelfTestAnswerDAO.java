package com.skybox.seven.covid.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.skybox.seven.covid.data.entities.SelfTestAnswer;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface SelfTestAnswerDAO {
    String TABLE_NAME = "SelfTestAnswers";

    @Query("SELECT * FROM " + TABLE_NAME)
    List<SelfTestAnswer> getAll();

    @Insert
    Completable insertAll(List<SelfTestAnswer> tests);

    @Delete
    void delete(SelfTestAnswer test);
}
