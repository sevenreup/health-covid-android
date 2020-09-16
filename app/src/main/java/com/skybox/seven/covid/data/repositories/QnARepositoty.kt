package com.skybox.seven.covid.data.repositories

import com.skybox.seven.covid.data.daos.QnADAO
import javax.inject.Inject

class QnARepositoty @Inject constructor(private val qnADAO: QnADAO) {
    fun getAllQnA() = qnADAO.getAllQnA(0)
}