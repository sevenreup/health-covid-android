package com.skybox.seven.covid.data.entities


data class Qna(var type: String, var question: String, var answer: String)

fun getQnaData () = listOf(

        Qna("Qna","What is covid19?", "Corona virus disease 2019"),
        Qna("Qna","What is covid19?", "Corona virus disease 2019"),
        Qna("Qna","What is covid19?", "Corona virus disease 2019")

)