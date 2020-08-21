package com.skybox.seven.covid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this

        return binding.root
    }

    fun navigateContacts() {
        findNavController().navigate(R.id.to_contacts)
    }

    fun navigateAddContacts() {
        findNavController().navigate(R.id.to_contactCreate)
    }

    fun navigateSelfTest() {
        findNavController().navigate(R.id.to_selfTest_view)
    }
}