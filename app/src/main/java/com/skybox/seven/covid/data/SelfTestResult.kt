package com.skybox.seven.covid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SelfTestResultDAO.TABLE_NAME)
class SelfTestResult {
    @PrimaryKey
    var id: Int = 0

    var reply: String = ""
    var status: String = ""

}