package com.skybox.seven.covid.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skybox.seven.covid.data.daos.SelfTestQuestionDAO


@Entity(tableName = SelfTestQuestionDAO.TABLE_NAME)
data class SelfTestQuestion(@PrimaryKey(autoGenerate = true) var id: Int?,
                            var questionID: Int,
                            var question: String?,
                            var subTitle: String?,
                            var type: Int,
                            var locale: Int,
                            var arrays: ArrayList<String>?)