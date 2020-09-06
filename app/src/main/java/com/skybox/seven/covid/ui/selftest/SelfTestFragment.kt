package com.skybox.seven.covid.ui.selftest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentSelftestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfTestFragment: Fragment() {
    private lateinit var binding: FragmentSelftestBinding
    private val viewModel: SelfTestViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelftestBinding.inflate(inflater, container, false)
        binding.startTest.setOnClickListener {
            findNavController().navigate(R.id.start_self_test)
        }
        return  binding.root
    }
}