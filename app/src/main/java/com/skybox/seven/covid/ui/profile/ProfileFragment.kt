package com.skybox.seven.covid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.Covid
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentProfileBinding
import com.skybox.seven.covid.ui.MainViewModel
import com.skybox.seven.covid.util.Constants
import com.yariksoffice.lingver.Lingver
import java.util.*


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    val viewModel: ProfileViewModel by activityViewModels()
    val mainViewModel: MainViewModel by activityViewModels()

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
        findNavController().navigate(R.id.to_selfTest)
    }

    fun getGreeting(): String {
        return ""
    }

    fun changelanguage() {
        if (Lingver.getInstance().getLocale() != Locale("ny", "MW"))
            Lingver.getInstance().setLocale(requireContext(), Locale("ny", "MW"))
        else
            Lingver.getInstance().setLocale(requireContext(), Locale(Covid.LANGUAGE_ENGLISH))
    }
}