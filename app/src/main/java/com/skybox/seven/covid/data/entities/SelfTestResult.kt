package com.skybox.seven.covid.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skybox.seven.covid.data.daos.SelfTestResultDAO

@Entity(tableName = SelfTestResultDAO.TABLE_NAME)
class SelfTestResult {
    @PrimaryKey
    var id: Int = 0

    var reply: String = ""
    var status: String = ""

}