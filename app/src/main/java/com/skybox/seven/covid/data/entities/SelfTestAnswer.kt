package com.skybox.seven.covid.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO
import java.util.*

@Entity(tableName = SelfTestAnswerDAO.TABLE_NAME)
data class SelfTestAnswer(@PrimaryKey(autoGenerate = true) var id: Int?,
                          var type: Int,
                          var questionID: Int,
                          var answerArray: List<Int>?,
                          var answerBoolean: Boolean?,
                          val createdAt: Date)