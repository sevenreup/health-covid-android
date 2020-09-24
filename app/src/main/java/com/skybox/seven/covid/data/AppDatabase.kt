package com.skybox.seven.covid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.*
import com.skybox.seven.covid.BuildConfig
import com.skybox.seven.covid.data.daos.*
import com.skybox.seven.covid.data.entities.*
import com.skybox.seven.covid.helpers.RoomTypeConverters
import com.skybox.seven.covid.work.DataBaseInitWorker
import com.skybox.seven.covid.work.GrabSelfTestsWorker
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Database(entities = [SelfTestAnswer::class, SelfTestQuestion::class, SelfTestComplete::class, Myth::class, Advice::class, InfoGraphic::class, Language::class, Qna::class], version = BuildConfig.HEALTH_DB_VERSION)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun selfTestAnswerDAO(): SelfTestAnswerDAO
    abstract fun selfTestQuestionDAO(): SelfTestQuestionDAO
    abstract fun selfTestCompleteDAO(): SelfTestCompleteDAO
    abstract fun mythsDAO(): MythsDAO
    abstract fun languageDAO(): LanguageDAO
    abstract fun adviceDAO(): AdviceDAO
    abstract fun infoGraphicDAO(): InfoGraphicDAO
    abstract fun qnADAO(): QnADAO

    companion object {
        private const val DB_NAME = "MalawiHealth"
        @JvmStatic
        fun getDatabase(context: Context): AppDatabase =Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Executors.newSingleThreadExecutor().execute {
                                        val workRequest = OneTimeWorkRequest.Builder(DataBaseInitWorker::class.java).build()

                                        val constraints = Constraints.Builder()
                                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                                .build()

                                        val selfTest = OneTimeWorkRequest.Builder(GrabSelfTestsWorker::class.java)
                                                .addTag(GrabSelfTestsWorker.TAG)
                                                .setConstraints(constraints)
                                                .setBackoffCriteria(
                                                        BackoffPolicy.LINEAR,
                                                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                                                        TimeUnit.MILLISECONDS
                                                )
                                                .build()
                                        WorkManager.getInstance(context).enqueue(arrayListOf(workRequest, selfTest))
                                    }
                                }
                            })
                            .build()
                }
}