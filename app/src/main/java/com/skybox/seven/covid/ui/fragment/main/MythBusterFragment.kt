package com.skybox.seven.covid.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.adapters.MythsAdapter
import com.skybox.seven.covid.ui.adapters.MythsModel

class MythBusterFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val display = inflater.inflate(R.layout.fragment_myth_buster, container, false)


        val arrayList = ArrayList<MythsModel>()

        arrayList.add(MythsModel("1","What is Corona Virus?"))
        arrayList.add(MythsModel("2","What is COVID-19"))
        arrayList.add(MythsModel("3","What are the symptoms of COVID-19?"))
        arrayList.add(MythsModel("4","How does COVID-19 spread?"))
        arrayList.add(MythsModel("5","How do l protect myself and prevent the spread of COVID-19?"))
        arrayList.add(MythsModel("6","How likely l am to catch COVID-19?"))
        arrayList.add(MythsModel("7","Should l worry about COVID19?"))
        arrayList.add(MythsModel("8","Who is at risk of developing severe illness?"))
        arrayList.add(MythsModel("9","Are antibiotics effective in treating or preventing COVID19?"))
        arrayList.add(MythsModel("10","Are there therapies or medicines that can prevent or cure COVID-19?"))

        arrayList.add(MythsModel("11","Is there a vaccine, drug or treatment for COVID19?"))
        arrayList.add(MythsModel("12","Is COVID-19 the same as SARS?"))
        arrayList.add(MythsModel("13","Should l wear a mask to protect myself?"))
        arrayList.add(MythsModel("14","How to put on, use, take off and dispose of a mask?"))
        arrayList.add(MythsModel("15","How long is the incubation period of COVID19?"))
        arrayList.add(MythsModel("16","Can humans be infected with the COVID-19 from animal an source?"))

        arrayList.add(MythsModel("17","Can l catch COVID19 from my pets?"))
        arrayList.add(MythsModel("18","How long does the virus survive on surfaces?"))
        arrayList.add(MythsModel("19","Is it safe to receive a package from an area where COVID19 has been reported?"))
        arrayList.add(MythsModel("20","Is there anything l should not do?"))
        arrayList.add(MythsModel("21","Is the source of coronavirus causing COVID19 known?"))
        arrayList.add(MythsModel("22","How did the first human SARS-CoV-2 infections occur?"))
        arrayList.add(MythsModel("23","Is COVID-19 airborne?"))



        val adapter = MythsAdapter(arrayList,this@MythBusterFragment)

        val recycler = display.findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter

        return display
    }

}
