package com.skybox.seven.covid.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.adapters.QnaAdapter
import com.skybox.seven.covid.ui.adapters.QnaModel

class QnAFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val display = inflater.inflate(R.layout.fragment_qna, container, false)


        val arrayList = ArrayList<QnaModel>()

        arrayList.add(QnaModel("1","What is Corona Virus?"))
        arrayList.add(QnaModel("2","What is COVID-19"))
        arrayList.add(QnaModel("3","What are the symptoms of COVID-19?"))
        arrayList.add(QnaModel("4","How does COVID-19 spread?"))
        arrayList.add(QnaModel("5","How do l protect myself and prevent the spread of COVID-19?"))
        arrayList.add(QnaModel("6","How likely l am to catch COVID-19?"))
        arrayList.add(QnaModel("7","Should l worry about COVID19?"))
        arrayList.add(QnaModel("8","Who is at risk of developing severe illness?"))
        arrayList.add(QnaModel("9","Are antibiotics effective in treating or preventing COVID19?"))
        arrayList.add(QnaModel("10","Are there therapies or medicines that can prevent or cure COVID-19?"))

        arrayList.add(QnaModel("11","Is there a vaccine, drug or treatment for COVID19?"))
        arrayList.add(QnaModel("12","Is COVID-19 the same as SARS?"))
        arrayList.add(QnaModel("13","Should l wear a mask to protect myself?"))
        arrayList.add(QnaModel("14","How to put on, use, take off and dispose of a mask?"))
        arrayList.add(QnaModel("15","How long is the incubation period of COVID19?"))
        arrayList.add(QnaModel("16","Can humans be infected with the COVID-19 from animal an source?"))

        arrayList.add(QnaModel("17","Can l catch COVID19 from my pets?"))
        arrayList.add(QnaModel("18","How long does the virus survive on surfaces?"))
        arrayList.add(QnaModel("19","Is it safe to receive a package from an area where COVID19 has been reported?"))
        arrayList.add(QnaModel("20","Is there anything l should not do?"))
        arrayList.add(QnaModel("21","Is the source of coronavirus causing COVID19 known?"))
        arrayList.add(QnaModel("22","How did the first human SARS-CoV-2 infections occur?"))
        arrayList.add(QnaModel("23","Is COVID-19 airborne?"))



        val adapter = QnaAdapter(arrayList,this@QnAFragment)

        val recycler = display.findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter

        return display
    }

}
