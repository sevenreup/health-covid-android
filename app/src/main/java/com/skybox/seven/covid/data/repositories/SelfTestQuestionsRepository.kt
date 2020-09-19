package com.skybox.seven.covid.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.skybox.seven.covid.data.daos.SelfTestQuestionDAO
import com.skybox.seven.covid.data.entities.SelfTestQuestion
import javax.inject.Inject

class SelfTestQuestionsRepository @Inject constructor(private val questionDAO: SelfTestQuestionDAO) {
    fun getAll(locale: Int): LiveData<List<SelfTestQuestion>> {
        Log.e("TAG", "getAll: $locale")
        return questionDAO.getAll(locale)
    }
}