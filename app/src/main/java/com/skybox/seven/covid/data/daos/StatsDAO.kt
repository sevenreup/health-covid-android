package com.skybox.seven.covid.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skybox.seven.covid.data.entities.CountriesModel

@Dao
interface StatsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(question: List<CountriesModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountriesModel)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): LiveData<List<CountriesModel>>

    companion object {
        const val TABLE_NAME = "countries"
    }
}