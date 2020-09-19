package com.skybox.seven.covid.work

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.iid.FirebaseInstanceId
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import java.util.concurrent.CountDownLatch

private const val TAG = "TokenWorker"
class TokenWorker @WorkerInject constructor(@Assisted private val context: Context,
                                            @Assisted private val workerParams: WorkerParameters,
                                            private val healthService: HealthService,
                                            private val preferenceRepository: SharedPreferenceRepository) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val token = workerParams.inputData.getString("token")
        if (token != null) {
            val result = healthService.pushToken(preferenceRepository.token, token).execute()
            return if (result.isSuccessful)
                Result.success()
            else
                result.errorBody()?.bytes()?.let { Data.fromByteArray(it) }?.let { Result.failure(it) } ?: Result.failure()
        } else {
            var outWard: String? = ""
            val latch = CountDownLatch(1)
            FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
                if (it.isSuccessful) {
                    outWard = it.result?.token
                    latch.countDown()
                }
            }
            latch.await()
            Log.e(TAG, "doWork: $outWard")
            val result =  healthService.pushToken(preferenceRepository.token, outWard).execute()
            return if (result.isSuccessful)
                Result.success()
            else
                result.errorBody()?.bytes()?.let { Data.fromByteArray(it) }?.let { Result.failure(it) } ?: Result.failure()
        }
    }

}