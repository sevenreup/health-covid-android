package com.skybox.seven.covid.data.repositories

import androidx.lifecycle.LiveData
import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO
import com.skybox.seven.covid.data.daos.SelfTestCompleteDAO
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.data.entities.SelfTestComplete
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

class SelfTestAnswersRepository @Inject constructor(private val selfTestAnswerDAO: SelfTestAnswerDAO,
                                                    private val selfTestCompleteDAO: SelfTestCompleteDAO) {

    fun insertAllAnswers(answers: List<SelfTestAnswer>): Completable = selfTestAnswerDAO.insertAll(answers)

    fun insertComplete(complete: SelfTestComplete): Single<Long> = selfTestCompleteDAO.insertComplete(complete)

    fun getTodayTest(): LiveData<SelfTestComplete?> {
        val start = Calendar.getInstance()
        with(start) {
            set(Calendar.HOUR_OF_DAY,0);
            set(Calendar.MINUTE,0);
            set(Calendar.SECOND,0);
            set(Calendar.MILLISECOND,0);
        }
        val end = Calendar.getInstance()
        with(end) {
            set(Calendar.HOUR_OF_DAY,23);
            set(Calendar.MINUTE,59);
            set(Calendar.SECOND,59);
            set(Calendar.MILLISECOND,0);
        }
        return selfTestCompleteDAO.getTodayResults(start.time, end.time)
    }

    fun getLastUpdate(): LiveData<Date?> = selfTestCompleteDAO.getLatest()

    fun getTopSelfTests() = selfTestCompleteDAO.getLatestThree()

    fun getAllTest() = selfTestCompleteDAO.getAll()
}