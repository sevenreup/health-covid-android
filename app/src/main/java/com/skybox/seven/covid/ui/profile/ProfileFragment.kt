package com.skybox.seven.covid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentProfileBinding
import com.skybox.seven.covid.ui.MainViewModel
import com.skybox.seven.covid.util.Constants


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: MainViewModel by activityViewModels()

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
        viewModel.language = Constants.CHICHEWA
//        findNavController().navigate(R.id.to_selfTest_view)
    }
}