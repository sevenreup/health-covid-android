package com.skybox.seven.covid.ui.selftest

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.skybox.seven.covid.data.repositories.SelfTestAnswersRepository
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import com.skybox.seven.covid.work.GrabSelfTestsWorker
import java.util.concurrent.TimeUnit

private const val TAG = "SelfTestViewModel"
class SelfTestViewModel @ViewModelInject constructor(selfTestAnswersRepository: SelfTestAnswersRepository, private val preferenceRepository: SharedPreferenceRepository) : ViewModel() {
    var todayTest = selfTestAnswersRepository.getTodayTest()
    var latestTest = selfTestAnswersRepository.getLastUpdate()
    val topThree = selfTestAnswersRepository.getTopSelfTests()

    val selfTestsDownloaded = MutableLiveData<Boolean>(preferenceRepository.testsLoaded)
    val active = MutableLiveData<Boolean>(true)
    val failed = MutableLiveData<Boolean>()

    fun isAuth(): Boolean = preferenceRepository.isLoggedIn

    fun reFetchData(context: Context, owner: LifecycleOwner) {
        active.value = false
        val workManager = WorkManager.getInstance(context)

        workManager.cancelAllWorkByTag(GrabSelfTestsWorker.TAG)

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val selfTest = OneTimeWorkRequest.Builder(GrabSelfTestsWorker::class.java)
                .setConstraints(constraints)
                .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS
                )
                .build()

        workManager.enqueue(selfTest)
        workManager.getWorkInfoByIdLiveData(selfTest.id).observe(owner, Observer {
            Log.e(TAG, "reFetchData: here")
            if (it != null) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    selfTestsDownloaded.value = preferenceRepository.testsLoaded
                } else if (it.state == WorkInfo.State.FAILED || it.state == WorkInfo.State.CANCELLED) {
                    active.value = true
                    failed.value = true
                }
            }
        })
    }
}