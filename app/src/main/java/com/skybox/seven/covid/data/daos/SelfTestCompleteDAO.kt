package com.skybox.seven.covid.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.skybox.seven.covid.data.entities.SelfTestComplete
import io.reactivex.rxjava3.core.Single
import java.util.*

@Dao
interface SelfTestCompleteDAO {
    @Insert
    fun insertComplete(test: SelfTestComplete): Single<Long>

    @Update
    fun update(test: SelfTestComplete)

    @Query("SELECT * FROM $TABLE_NAME WHERE date BETWEEN :start AND :end")
    fun getTodayResults(start: Date, end: Date): LiveData<SelfTestComplete?>

    @Query("SELECT * FROM $TABLE_NAME WHERE date BETWEEN :start AND :end")
    fun getTodayResultsSync(start: Date, end: Date): SelfTestComplete?

    @Query("SELECT date FROM $TABLE_NAME ORDER BY date DESC LIMIT 1")
    fun getLatest(): LiveData<Date?>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY date DESC LIMIT 3")
    fun getLatestThree(): LiveData<List<SelfTestComplete>>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY date DESC")
    fun getAll(): LiveData<List<SelfTestComplete>>

    @Query("SELECT * FROM $TABLE_NAME WHERE submitted = :submitted")
    fun getUnUploaded(submitted: Boolean): List<SelfTestComplete>


    companion object {
        const val TABLE_NAME = "SelfTestComplete"
    }
}