package com.skybox.seven.covid.ui.selftest.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.skybox.seven.covid.databinding.FragmentAllTestsBinding
import com.skybox.seven.covid.epoxy.selftest.SelfTestController
import com.skybox.seven.covid.ui.selftest.SelfTestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTestsFragment : Fragment() {
    private lateinit var binding: FragmentAllTestsBinding
    private val viewModel: AllTestsViewModel by viewModels()
    private var controller = SelfTestController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.all.observe(this, Observer {
            controller.setData(false, it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAllTestsBinding.inflate(inflater, container, false)
        binding.recycler.setController(controller)
        return binding.root
    }
}