package com.skybox.seven.covid.helpers

import java.text.NumberFormat
import java.util.*

object TextFormatter {
    fun Int?.formatNumber(): String? {
        return try {
            NumberFormat.getNumberInstance(Locale.US).format(this)
        } catch (ex: IllegalArgumentException) {
            this?.toString()
        }
    }
}