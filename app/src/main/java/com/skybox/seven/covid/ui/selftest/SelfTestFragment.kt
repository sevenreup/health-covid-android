package com.skybox.seven.covid.ui.selftest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentSelftestBinding
import com.skybox.seven.covid.epoxy.selftest.SelfTestController
import dagger.hilt.android.AndroidEntryPoint
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

@AndroidEntryPoint
class SelfTestFragment : Fragment() {
    private lateinit var binding: FragmentSelftestBinding
    val viewModel: SelfTestViewModel by viewModels()
    val controller: SelfTestController = SelfTestController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.topThree.observe(this, androidx.lifecycle.Observer {
            controller.setData(false, it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelftestBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        binding.historyRecy.setController(controller)
        binding.historyRecy.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.todayTest.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == null) binding.startTest.setOnClickListener {findNavController().navigate(R.id.start_self_test) }
            else binding.startTest.setOnClickListener(null)
        })
        return binding.root
    }

    fun formatDate(date: Date?): String {
        return PrettyTime().format(date)
    }
    fun all() {
        findNavController().navigate(R.id.to_all_test)
    }
}