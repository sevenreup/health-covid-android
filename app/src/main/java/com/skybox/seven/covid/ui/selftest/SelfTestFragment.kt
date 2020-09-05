package com.skybox.seven.covid.ui.selftest

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
import com.skybox.seven.covid.databinding.FragmentSelftestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_selftest.*

@AndroidEntryPoint
class SelfTestFragment: Fragment() {
    private lateinit var binding: FragmentSelftestBinding
    private val viewModel: SelfTestViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelftestBinding.inflate(inflater, container, false)

        return  binding.root
    }
}