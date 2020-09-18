package com.skybox.seven.covid.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.skybox.seven.covid.data.entities.Qna

@Dao
interface QnADAO {
    @Query("SELECT * From questinandanswer WHERE locale = :id")
    fun getAllQnA(id: Int): LiveData<List<Qna>>

    @Insert
    fun insertAll(qna: List<Qna>)

    @Insert
    fun insert(qna: Qna)
    companion object {
        const val TABLE_NAME = "QuestinAndAnswer"
    }
}