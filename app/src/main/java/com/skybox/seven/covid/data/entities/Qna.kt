package com.skybox.seven.covid.data.entities

import com.skybox.seven.covid.R

data class Qna(var pos: Int, var arrow: Int, var question: String, var answer: String)

fun getQnaData () = listOf(

        Qna(1, R.drawable.ic_keyboard_arrow,"What is covid19?", "Coronaviruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19."),
        Qna(2,R.drawable.ic_keyboard_arrow,"What is covid19?", "Corona virus disease 2019"),
        Qna(3,R.drawable.ic_keyboard_arrow,"What is covid19?", "Corona virus disease 2019"),
        Qna(4,R.drawable.ic_keyboard_arrow,"What is covid19?", "Corona virus disease 2019"),
        Qna(5,R.drawable.ic_keyboard_arrow,"What is covid19?", "Corona virus disease 2019"),
        Qna(6,R.drawable.ic_keyboard_arrow,"What is covid19?", "Coronaviruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19.")

)