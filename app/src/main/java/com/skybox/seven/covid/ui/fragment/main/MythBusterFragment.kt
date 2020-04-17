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

        arrayList.add(MythsModel("1","What is covid?"))
        arrayList.add(MythsModel("2","Halla!"))



        val adapter = MythsAdapter(arrayList,this@MythBusterFragment)

        val recycler = display.findViewById(R.id.recycler) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter

        return display
    }

}
