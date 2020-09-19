package com.skybox.seven.covid.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skybox.seven.covid.data.entities.SelfTestQuestion
import io.reactivex.rxjava3.core.Completable

@Dao
interface SelfTestQuestionDAO {
    @Query("SELECT * FROM $TABLE_NAME WHERE locale = :locale")
    fun getAll(locale: Int): LiveData<List<SelfTestQuestion>>

    @Insert
    fun insertAll(question: List<SelfTestQuestion>)

    @Delete
    fun delete(question: SelfTestQuestion)

    companion object {
        const val TABLE_NAME = "SelfTestQuestions"
    }
}