package com.skybox.seven.covid.ui.profile.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.skybox.seven.covid.R
import com.skybox.seven.covid.adapters.ContactsPageAdapter
import com.skybox.seven.covid.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : Fragment() {
    lateinit var binding: FragmentContactsBinding
    val viewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentContactsBinding.inflate(inflater, container, false)

        viewModel.networkLoading.observe(this, Observer { aBoolean: Boolean ->
            if (aBoolean) {
                binding.errorHolder.visibility = View.VISIBLE
            } else {
                binding.errorHolder.visibility = View.GONE
            }
        })
        val pagerAdapter = ContactsPageAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.contactTab, binding.viewPager, TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.setText(R.string.contacts_title)
                1 -> tab.setText(R.string.contacts_request_title)
            }
        }).attach()

        return binding.root
    }

    fun back() {
        findNavController().navigateUp()
    }
}