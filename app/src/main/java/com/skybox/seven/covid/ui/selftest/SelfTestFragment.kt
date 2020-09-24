package com.skybox.seven.covid.ui.selftest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentSelftestBinding
import com.skybox.seven.covid.epoxy.selftest.SelfTestController
import com.skybox.seven.covid.ui.AuthActivity
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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelftestBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        binding.historyRecy.setController(controller)
        binding.historyRecy.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.todayTest.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == null) binding.startTest.setOnClickListener {
                if (viewModel.isAuth()) {
                    findNavController().navigate(R.id.start_self_test)
                } else {
                    MaterialAlertDialogBuilder(requireContext())
                            .setTitle(resources.getString(R.string.self_test_dialog_title))
                            .setMessage(resources.getString(R.string.sel_test_supporting_text))
                            .setNeutralButton(resources.getString(R.string.cancel)) { _, _ ->
                                findNavController().navigate(R.id.start_self_test)
                            }
                            .setNegativeButton(resources.getString(R.string.register)) { _, _ ->
                                startActivity(Intent(context, AuthActivity::class.java).apply { putExtra(AuthActivity.LOGIN, false) })
                            }
                            .setPositiveButton(resources.getString(R.string.login)) { _, _ ->
                                startActivity(Intent(context, AuthActivity::class.java).apply { putExtra(AuthActivity.LOGIN, true) })
                            }
                            .show()
                }
            }
            else binding.startTest.setOnClickListener(null)
        })
        viewModel.topThree.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            controller.setData(false, it)
            if (it.isNotEmpty()) binding.allTestHolder.setOnClickListener { all() }
        })
        return binding.root
    }

    fun formatDate(date: Date?): String {
        return PrettyTime().format(date)
    }
    fun all() {
        findNavController().navigate(R.id.to_all_test)
    }

    fun back() {
        findNavController().navigateUp()
    }
}