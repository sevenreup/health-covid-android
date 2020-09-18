package com.skybox.seven.covid.model

import com.skybox.seven.covid.data.entities.Advice
import com.skybox.seven.covid.data.entities.ContactDetail
import com.skybox.seven.covid.data.entities.Myth
import com.skybox.seven.covid.data.entities.Qna

data class RawData(val prevention: List<Advice>, val myth: List<Myth>, val questions: List<Qna>, val contacts: ContactDetail)