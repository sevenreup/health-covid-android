package com.skybox.seven.survey.answer

import android.os.Parcelable
import java.util.*

interface Result: Parcelable {
    val id: String
    val startDate: Date
    var endDate: Date
}