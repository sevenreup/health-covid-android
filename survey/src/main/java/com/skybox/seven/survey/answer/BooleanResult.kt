package com.skybox.seven.survey.answer

import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class BooleanResult(override val id: String,
                         override val startDate: Date,
                         override var endDate: Date,
                         val answer: Boolean): Result