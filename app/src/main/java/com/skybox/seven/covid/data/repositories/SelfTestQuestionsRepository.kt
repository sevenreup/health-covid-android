package com.skybox.seven.covid.data.repositories

import com.skybox.seven.covid.data.daos.SelfTestQuestionDAO
import javax.inject.Inject

class SelfTestQuestionsRepository @Inject constructor(private val questionDAO: SelfTestQuestionDAO) {
    fun getAll() = questionDAO.getAll()
}