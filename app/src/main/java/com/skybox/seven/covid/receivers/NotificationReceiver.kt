package com.skybox.seven.covid.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.skybox.seven.covid.work.SelfTestReminderWorker

/**
 * Handle alarm manager
 */

class NotificationReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive: here")
        if (context != null) {
            val request = OneTimeWorkRequest.Builder(SelfTestReminderWorker::class.java).build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
    companion object {
        private const val TAG = "NotificationReceiver"
    }
}