package com.skybox.seven.covid.widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.skybox.seven.covid.R
import com.skybox.seven.covid.work.WidgetWorker
import java.util.concurrent.TimeUnit

class MWCovidWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        Log.e(TAG, "onUpdate: $appWidgetIds")
        // There may be multiple widgets active, so update all of them
//        for (appWidgetId in appWidgetIds) {
//
//            val intent = Intent(context, MWCovidService::class.java)
//            intent.action = "refresh"
//            intent.putExtra(ID, appWidgetId)
//            context.startService(intent)
//        }
                    val requests = PeriodicWorkRequest.Builder(WidgetWorker::class.java, 30, TimeUnit.MINUTES)
                            .setInputData(Data.Builder().putIntArray(ID, appWidgetIds)
                            .build())
                            .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(NAME, ExistingPeriodicWorkPolicy.REPLACE, requests)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        const val ID = "widget_id"
        const val NAME = "widget_update"
    }
}

private const val TAG = "MWCovidService"
class MWCovidService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null && intent.hasExtra(MWCovidWidget.ID)) {
            val appWidgetId = intent.getIntExtra(MWCovidWidget.ID, 0);
            Log.d(TAG, "onStartCommand($appWidgetId)");
            updateWidget(appWidgetId)
        } else {
            Log.e(TAG, "onStartCommand(<no widgetId>)");
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun updateWidget(widgetId: Int) {
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        val views = RemoteViews(application.packageName, R.layout.m_w_covid_widget)

        appWidgetManager.updateAppWidget(widgetId, views)
    }
}