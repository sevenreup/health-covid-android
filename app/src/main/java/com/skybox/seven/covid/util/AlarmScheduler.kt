package com.skybox.seven.covid.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.skybox.seven.covid.receivers.NotificationReceiver
import java.util.*

object AlarmScheduler {
    fun createAlarms(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val morning = Calendar.getInstance()
        morning.set(Calendar.HOUR_OF_DAY, 12)
        morning.set(Calendar.MINUTE, 0)

        val evening = Calendar.getInstance()
        evening.set(Calendar.HOUR_OF_DAY, 17)
        evening.set(Calendar.MINUTE, 0)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, morning.timeInMillis, AlarmManager.INTERVAL_DAY, createPendingMorningIntent(context, 0))
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, evening.timeInMillis, AlarmManager.INTERVAL_DAY, createPendingMorningIntent(context, 1))
    }

    private fun createPendingMorningIntent(context: Context, time: Int): PendingIntent? {
        val intent = Intent(context.applicationContext, NotificationReceiver::class.java).apply {
            putExtra("TIME", time)
        }
        return PendingIntent.getBroadcast(context, time, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}