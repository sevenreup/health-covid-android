package com.skybox.seven.covid.ui.selftest.all

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.skybox.seven.covid.databinding.FragmentAllTestsBinding
import com.skybox.seven.covid.epoxy.selftest.SelfTestController
import com.skybox.seven.covid.ui.AuthActivity
import com.skybox.seven.covid.ui.selftest.SelfTestViewModel
import com.skybox.seven.covid.work.SubmitSelfTests
import com.skybox.seven.covid.work.TokenWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTestsFragment : Fragment() {
    private lateinit var binding: FragmentAllTestsBinding
    val viewModel: AllTestsViewModel by viewModels()
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
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recycler.setController(controller)
        binding.submit.setOnClickListener {
            // todo: Fix this, this is gay
            val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            val worker = OneTimeWorkRequest.Builder(SubmitSelfTests::class.java)
                    .setConstraints(constraints)
                    .build()
            WorkManager.getInstance(requireContext()).enqueue(worker);
        }
        return binding.root
    }

    fun login() {
        startActivity(Intent(context, AuthActivity::class.java).apply { putExtra(AuthActivity.LOGIN, true) })
    }

    fun register() {
        startActivity(Intent(context, AuthActivity::class.java).apply { putExtra(AuthActivity.LOGIN, false) })
    }
}