package com.skybox.seven.covid.work

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.skybox.seven.covid.data.AppDatabase
import com.skybox.seven.covid.data.entities.SelfTestQuestion
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import com.skybox.seven.covid.util.Constants

private const val TAG = "GrabSelfTestsWorker"
class GrabSelfTestsWorker @WorkerInject constructor(@Assisted private val context: Context,
                                                    @Assisted workerParams: WorkerParameters,
                                                    private val db: AppDatabase,
                                                    private val healthService: HealthService,
                                                    private val preferenceRepository: SharedPreferenceRepository) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val response = healthService.getSelfTests(preferenceRepository.token).execute()

        if (response.isSuccessful) {
            val questions: ArrayList<SelfTestQuestion> = ArrayList()

            response.body()?.data?.forEach {
                val questionEN = SelfTestQuestion(
                        null, it.id,
                        it.questionsEnglish, it.subtitleEnglish,
                        it.type, Constants.ENGLISH,
                        it.answersEnglish as ArrayList<String>?
                )

                val questionNy = SelfTestQuestion(
                        null, it.id,
                        it.questionsChichewa, it.subtitleChichewa,
                        it.type, Constants.CHICHEWA,
                        it.answersChichewa as ArrayList<String>?
                )

                questions.add(questionEN)
                questions.add(questionNy)
            }
            Log.e(TAG, "doWork: ${questions.size}")
            db.selfTestQuestionDAO().insertAll(questions)
            return Result.success()
        } else {
            return Result.failure()
        }

    }

}