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

        val date = Calendar.getInstance()
        date.set(Calendar.HOUR_OF_DAY, 12)
        date.set(Calendar.MINUTE, 0)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, date.timeInMillis, AlarmManager.INTERVAL_DAY, createPendingIntent(context))
    }

    private fun createPendingIntent(context: Context): PendingIntent? {
        val intent = Intent(context.applicationContext, NotificationReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}