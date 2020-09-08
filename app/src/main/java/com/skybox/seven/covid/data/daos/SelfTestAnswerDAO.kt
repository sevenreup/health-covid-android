package com.skybox.seven.covid.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.entities.SelfTestComplete
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.util.*

@Dao
interface SelfTestAnswerDAO {

    @Query("SELECT * FROM $TABLE_NAME WHERE createdAt BETWEEN :start AND :end")
    fun getTodayResults(start: Date, end: Date): LiveData<List<SelfTestAnswer>>

    @Query("SELECT * FROM $TABLE_NAME")
    fun all(): List<SelfTestAnswer>?


    @Query("SELECT createdAt FROM $TABLE_NAME ORDER BY createdAt LIMIT 1")
    fun getLatest(): LiveData<Date>

    @Insert
    fun insertAll(tests: List<SelfTestAnswer>): Completable

    @Delete
    fun delete(test: SelfTestAnswer)

    companion object {
        const val TABLE_NAME = "SelfTestAnswers"
    }
}