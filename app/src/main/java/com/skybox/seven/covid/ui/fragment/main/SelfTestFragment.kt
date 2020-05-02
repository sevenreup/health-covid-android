package com.skybox.seven.covid.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.databaseHandler2
import com.skybox.seven.covid.ui.adapters.selftestAdapter
import kotlinx.android.synthetic.main.selftest.*

class SelfTestFragment: Fragment() {

    companion object{
        lateinit var dbHandler2: databaseHandler2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.selftest, container, false)



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dbHandler2 = databaseHandler2(this.context!!, null, null ,1 )

        val testLists = dbHandler2.getCurrentTest(this.context!!)
        val adapter = selftestAdapter(testLists, this)

        val recycler: RecyclerView = view!!.findViewById(R.id.recyclerSelfTest)
        recycler.layoutManager = LinearLayoutManager(this.context!!, LinearLayout.VERTICAL, false)
        recycler.adapter = adapter

        selftest.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_selfTestFragment_to_actulTest_Fragment)
        }
    }

    override fun onResume() {
        val testLists = dbHandler2.getCurrentTest(this.context!!)
        val adapter = selftestAdapter(testLists, this)

        val recycler: RecyclerView = view!!.findViewById(R.id.recyclerSelfTest)
        recycler.layoutManager = LinearLayoutManager(this.context!!, LinearLayout.VERTICAL, false)
        recycler.adapter = adapter
        super.onResume()

    }
   //damn!
    //override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      //  super.onViewCreated(view, savedInstanceState)
//
  //      view.findViewById<Button>(R.id.selftest).setOnClickListener {
    //        findNavController(it).navigate(R.id.action_selfTestFragment_to_actulTest_Fragment)
      //  }
    //}




}