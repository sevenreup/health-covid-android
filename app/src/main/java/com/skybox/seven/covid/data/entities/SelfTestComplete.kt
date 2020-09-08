package com.skybox.seven.covid.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skybox.seven.covid.data.daos.SelfTestCompleteDAO
import java.util.*

@Entity(tableName = SelfTestCompleteDAO.TABLE_NAME)
data class SelfTestComplete(@PrimaryKey(autoGenerate = true) var id: Long?,
                            val title: String,
                            val date: Date,
                            val submitted: Boolean)