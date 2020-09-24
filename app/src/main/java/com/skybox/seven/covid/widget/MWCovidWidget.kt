package com.skybox.seven.covid.widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import com.skybox.seven.covid.R

class MWCovidWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            val intent = Intent(context, MWCovidService::class.java)
            intent.action = "refresh"
            intent.putExtra("widgetId", appWidgetId)
            context.startService(intent)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

private const val TAG = "MWCovidService"
class MWCovidService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null && intent.hasExtra("widgetId")) {
            val appWidgetId = intent.getIntExtra("widgetId", 0);
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