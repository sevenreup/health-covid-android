package com.skybox.seven.covid.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skybox.seven.covid.data.daos.StatsDAO
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = StatsDAO.TABLE_NAME)
@Parcelize
data class CountriesModel(@PrimaryKey val id: Int? = 0, var country: String, var iso3: String, var cases: String,var deaths: String, var recovered: String, var active: String, var lastUpDated: Date, var cacheDate: Date) : Parcelable {

}