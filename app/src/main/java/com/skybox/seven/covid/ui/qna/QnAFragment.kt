package com.skybox.seven.covid.ui.qna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.adapters.QnaAdapter
import com.skybox.seven.covid.adapters.QnaModel

class QnAFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val display = inflater.inflate(R.layout.fragment_qna, container, false)


        val arrayList = ArrayList<QnaModel>()
        val res = resources
        val items = res.getStringArray(R.array.questions)


        arrayList.add(QnaModel(getString(R.string.one),items[0]))
        arrayList.add(QnaModel(getString(R.string.two),items[1]))
        arrayList.add(QnaModel(getString(R.string.three),items[2]))
        arrayList.add(QnaModel(getString(R.string.four),items[3]))
        arrayList.add(QnaModel(getString(R.string.five),items[4]))
        arrayList.add(QnaModel(getString(R.string.six),items[5]))
        arrayList.add(QnaModel(getString(R.string.seven),items[6]))
        arrayList.add(QnaModel(getString(R.string.eight),items[7]))
        arrayList.add(QnaModel(getString(R.string.nine),items[8]))
        arrayList.add(QnaModel(getString(R.string.ten),items[9]))
        arrayList.add(QnaModel(getString(R.string.eleven),items[10]))
        arrayList.add(QnaModel(getString(R.string.twelve),items[11]))
        arrayList.add(QnaModel(getString(R.string.thirteen),items[12]))
        arrayList.add(QnaModel(getString(R.string.fourteen),items[13]))
        arrayList.add(QnaModel(getString(R.string.fifteen),items[14]))
        arrayList.add(QnaModel(getString(R.string.sixteen),items[15]))
        arrayList.add(QnaModel(getString(R.string.seventeen),items[16]))
        arrayList.add(QnaModel(getString(R.string.eighteen),items[17]))
        arrayList.add(QnaModel(getString(R.string.nineteen),items[18]))
        arrayList.add(QnaModel(getString(R.string.twenty),items[19]))
        arrayList.add(QnaModel(getString(R.string.twenty_one),items[20]))
        arrayList.add(QnaModel(getString(R.string.twenty_two),items[21]))






        val adapter = QnaAdapter(arrayList,this@QnAFragment)

        val recycler = display.findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter

        return display
    }

}
