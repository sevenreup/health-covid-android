package com.skybox.seven.covid.data.repositories

import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SelfTestAnswersRepository @Inject constructor(private val selfTestAnswerDAO: SelfTestAnswerDAO) {
    fun insertAll(answers: List<SelfTestAnswer>): Completable = selfTestAnswerDAO.insertAll(answers)
}