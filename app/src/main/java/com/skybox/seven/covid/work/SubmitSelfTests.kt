package com.skybox.seven.covid.work

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.skybox.seven.covid.data.AppDatabase
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.repository.SharedPreferenceRepository

private const val TAG = "GrabSelfTestsWorker"

class SubmitSelfTests @WorkerInject constructor(@Assisted private val context: Context,
                                                @Assisted workerParams: WorkerParameters,
                                                private val db: AppDatabase,
                                                private val healthService: HealthService,
                                                private val preferenceRepository: SharedPreferenceRepository) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val completeList = db.selfTestCompleteDAO().getUnUploaded(false)
        val list: ArrayList<SelfTestAnswer> = ArrayList()
        completeList.forEach {
            val answers = db.selfTestAnswerDAO().get(it.id!!)
            list.addAll(answers)
        }
        list.forEach {
            Log.e(TAG, "doWork: $it")
            healthService.insertSelfTestAnswer(preferenceRepository.token, it.questionID, it.answerArray, it.answerBoolean, it.longAnswer).execute()
        }
        completeList.forEach {
            db.selfTestCompleteDAO().update(it.apply { this.submitted = true })
        }
        return Result.success()
    }
}