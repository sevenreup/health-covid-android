package com.skybox.seven.covid.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.adapters.SelfTestAdapter
import com.skybox.seven.covid.data.entities.SelfTestResult
import com.skybox.seven.covid.viewmodels.SelfTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.selftest.*

@AndroidEntryPoint
class SelfTestFragment: Fragment(), SelfTestAdapter.SelfTestAdapterCallback {

    private val viewModel: SelfTestViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.selftest, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = SelfTestAdapter(this, this)
        viewModel.selfTestLiveData.observe(viewLifecycleOwner,  Observer<List<SelfTestResult>> { results -> adapter.setData(results) })
        viewModel.getAllTests()

        val recycler: RecyclerView = view!!.findViewById(R.id.recyclerSelfTest)
        recycler.layoutManager = LinearLayoutManager(this.context!!, LinearLayout.VERTICAL, false)
        recycler.adapter = adapter

        selftest.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_selfTestFragment_to_actulTest_Fragment)
        }
    }

    override fun onResume() {
        val adapter = SelfTestAdapter(this, this)
        viewModel.selfTestLiveData.observe(viewLifecycleOwner,  Observer<List<SelfTestResult>> { results -> adapter.setData(results) })
        viewModel.getAllTests()

        val recycler: RecyclerView = view!!.findViewById(R.id.recyclerSelfTest)
        recycler.layoutManager = LinearLayoutManager(this.context!!, LinearLayout.VERTICAL, false)
        recycler.adapter = adapter
        super.onResume()

    }

    override fun onDeleteClick(result: SelfTestResult) {
        viewModel.deleteTest(result)
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