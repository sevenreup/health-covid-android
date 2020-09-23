package com.skybox.seven.covid.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.skybox.seven.covid.util.AlarmScheduler

/**
 * Handle phone reboots to redo notification
 */
class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            AlarmScheduler.createAlarms(context)
        }
    }
}