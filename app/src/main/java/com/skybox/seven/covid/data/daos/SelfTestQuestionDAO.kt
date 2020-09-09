package com.skybox.seven.covid.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skybox.seven.covid.data.entities.SelfTestQuestion

@Dao
interface SelfTestQuestionDAO {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): LiveData<List<SelfTestQuestion>>

    @Insert
    fun insertAll(question: List<SelfTestQuestion>)

    @Delete
    fun delete(question: SelfTestQuestion)

    companion object {
        const val TABLE_NAME = "SelfTestQuestions"
    }
}