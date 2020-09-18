package com.skybox.seven.covid.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skybox.seven.covid.data.daos.QnADAO

@Entity(tableName = QnADAO.TABLE_NAME)
data class Qna(@PrimaryKey(autoGenerate = true) var id: Int, var question: String, var answer: String, var locale: Int)