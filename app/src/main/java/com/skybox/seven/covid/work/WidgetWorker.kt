package com.skybox.seven.covid.work

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.skybox.seven.covid.R
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.network.StatsService
import com.skybox.seven.covid.util.Constants
import com.skybox.seven.covid.util.toDate
import com.skybox.seven.covid.widget.MWCovidWidget
import org.ocpsoft.prettytime.PrettyTime


private const val TAG = "WidgetWorker"
class WidgetWorker  @WorkerInject constructor(@Assisted private val context: Context,
                                              @Assisted private val workerParams: WorkerParameters,
                                              private val statsService: StatsService) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val stats = statsService.getSingleCountryC(Constants.MW_ISO_3).execute()

        if (stats.isSuccessful) {
            workerParams.inputData.getIntArray(MWCovidWidget.ID)?.forEach {
                stats.body()?.let { data -> updateWidget(it, data) } ?: updateWidget(it)
            }
        } else {
            workerParams.inputData.getIntArray(MWCovidWidget.ID)?.forEach {
                updateWidget(it)
            }
        }

        return Result.success()
    }

    private fun updateWidget(widgetId: Int) {
        Log.e(TAG, "updateWidget: $widgetId")
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        val views = RemoteViews(context.packageName, R.layout.m_w_covid_widget)
        appWidgetManager.updateAppWidget(widgetId, views)
    }

    private fun updateWidget(widgetId: Int, data: CountryStat) {
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        val views = RemoteViews(context.packageName, R.layout.m_w_covid_widget)

        views.setTextViewText(R.id.recovered, data.recovered.toString())
        views.setTextViewText(R.id.active, data.active.toString())
        views.setTextViewText(R.id.deaths, data.deaths.toString())
        views.setTextViewText(R.id.update_time, data.updated.toDate())

        appWidgetManager.updateAppWidget(widgetId, views)
    }
}