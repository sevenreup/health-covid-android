package com.skybox.seven.covid.work

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.core.content.ContextCompat
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.daos.SelfTestCompleteDAO
import java.util.*
import kotlin.random.Random

class SelfTestReminderWorker @WorkerInject constructor(@Assisted private val context: Context,
                                                       @Assisted params: WorkerParameters,
                                                       private val selfTestCompleteDAO: SelfTestCompleteDAO): Worker(context, params) {
    override fun doWork(): Result {
        val start = Calendar.getInstance()
        with(start) {
            set(Calendar.HOUR_OF_DAY,0);
            set(Calendar.MINUTE,0);
            set(Calendar.SECOND,0);
            set(Calendar.MILLISECOND,0);
        }
        val end = Calendar.getInstance()
        with(end) {
            set(Calendar.HOUR_OF_DAY,23);
            set(Calendar.MINUTE,59);
            set(Calendar.SECOND,59);
            set(Calendar.MILLISECOND,0);
        }

        val today = selfTestCompleteDAO.getTodayResultsSync(start.time, end.time)

        if (today == null) sendNotification()

        return Result.success()
    }

    private fun sendNotification() {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.malawi.health.mw/selftest"))
        val bitmap = ContextCompat.getDrawable(context, R.drawable.headache)
        val pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val titleNotification = applicationContext.getString(R.string.self_notification_title)
        val subtitleNotification = applicationContext.getString(R.string.self_notification_body)

        val notification = NotificationCompat.Builder(applicationContext, context.getString(R.string.default_notification_channel_name))
//                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.virus)
                .setContentTitle(titleNotification).setContentText(subtitleNotification)
                .setDefaults(DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        if (SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(context.getString(R.string.default_notification_channel_name))
        }

        val notificationManager =
                applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(Random(1).nextInt(), notification.build())
    }

    companion object {
        const val TAG = "SELF)"
    }
}