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

        arrayList.add(QnaModel(getString(R.string.one),getString(R.string.q1)))
        arrayList.add(QnaModel(getString(R.string.two),getString(R.string.q2)))
        arrayList.add(QnaModel(getString(R.string.three),getString(R.string.q3)))
        arrayList.add(QnaModel(getString(R.string.four),getString(R.string.q4)))
        arrayList.add(QnaModel(getString(R.string.five),getString(R.string.q5)))
        arrayList.add(QnaModel(getString(R.string.six),getString(R.string.q6)))
        arrayList.add(QnaModel(getString(R.string.seven),getString(R.string.q7)))
        arrayList.add(QnaModel(getString(R.string.eight),getString(R.string.q8)))
        arrayList.add(QnaModel(getString(R.string.nine),getString(R.string.q9)))
        arrayList.add(QnaModel(getString(R.string.ten),getString(R.string.q10)))
        arrayList.add(QnaModel(getString(R.string.eleven),getString(R.string.q11)))
        arrayList.add(QnaModel(getString(R.string.twelve),getString(R.string.q12)))
        arrayList.add(QnaModel(getString(R.string.thirteen),getString(R.string.q13)))
        arrayList.add(QnaModel(getString(R.string.fourteen),getString(R.string.q14)))
        arrayList.add(QnaModel(getString(R.string.fifteen),getString(R.string.q15)))
        arrayList.add(QnaModel(getString(R.string.sixteen),getString(R.string.q16)))
        arrayList.add(QnaModel(getString(R.string.seventeen),getString(R.string.q17)))
        arrayList.add(QnaModel(getString(R.string.eighteen),getString(R.string.q18)))
        arrayList.add(QnaModel(getString(R.string.nineteen),getString(R.string.q19)))
        arrayList.add(QnaModel(getString(R.string.twenty),getString(R.string.q20)))
        arrayList.add(QnaModel(getString(R.string.twenty_one),getString(R.string.q21)))
        arrayList.add(QnaModel(getString(R.string.twenty_two),getString(R.string.q22)))
        arrayList.add(QnaModel(getString(R.string.twenty_three),getString(R.string.q23)))











        val adapter = QnaAdapter(arrayList,this@QnAFragment)

        val recycler = display.findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter

        return display
    }

}
