package com.skybox.seven.covid.util

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.skybox.seven.covid.data.AppDatabase
import com.skybox.seven.covid.model.RawData

private const val TAG = "DataBaseInitWorker"
class DataBaseInitWorker @WorkerInject constructor(@Assisted private val context: Context,
                                                   @Assisted workerParams: WorkerParameters, 
                                                   private val db: AppDatabase) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e(TAG, "doWork: satrt")
        val englishStream = context.assets.open("data/english.json").bufferedReader().use { it.readText() }
        val chichewaStream = context.assets.open("data/chichewa.json").bufferedReader().use { it.readText() }

        val gson = Gson()
        val english = gson.fromJson<RawData>(englishStream, RawData::class.java)
        val chichewa = gson.fromJson<RawData>(chichewaStream, RawData::class.java)

        Log.e(TAG, "doWork: $english $chichewa")

        db.adviceDAO().insertAll(english.prevention)
        db.mythsDAO().insertAll(english.myth)
        db.qnADAO().insertAll(english.questions)

        db.adviceDAO().insertAll(chichewa.prevention)
        db.mythsDAO().insertAll(chichewa.myth)
        db.qnADAO().insertAll(chichewa.questions)

        db.selfTestQuestionDAO().insertAll(RandomSeeders.setUpQuestions())

        db.languageDAO().insertAll(RandomSeeders.setUpLanguages())

        return Result.success()
    }
}