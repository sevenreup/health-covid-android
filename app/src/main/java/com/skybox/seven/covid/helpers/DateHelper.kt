package com.skybox.seven.covid.helpers

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun convertToRelative(dateTime: Date): String {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = dateTime
        val today: Calendar = Calendar.getInstance()
        val yesterday: Calendar = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)

        return if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(
                        Calendar.DAY_OF_YEAR
                )
        ) {
            SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(dateTime)
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(
                        Calendar.DAY_OF_YEAR
                ) == yesterday.get(Calendar.DAY_OF_YEAR)
        ) {
            "Yesterday"
        } else {
            SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(dateTime)
        }
    }
}