package com.skybox.seven.covid.ui.profile

import android.content.Intent
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
import com.skybox.seven.covid.ui.AuthActivity
import com.skybox.seven.covid.ui.MainViewModel
import com.skybox.seven.covid.util.Constants
import com.yariksoffice.lingver.Lingver
import org.ocpsoft.prettytime.PrettyTime
import java.util.*


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    val viewModel: ProfileViewModel by activityViewModels()

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

    fun goToLogin() {
        startActivity(Intent(context, AuthActivity::class.java).apply { putExtra(AuthActivity.LOGIN, true) })
    }

    fun goToRegister() {
        startActivity(Intent(context, AuthActivity::class.java).apply { putExtra(AuthActivity.LOGIN, false) })
    }

    fun getFormatDate(date: Date?): String {
        return PrettyTime().format(date)
    }

    fun goToSettings() {
        findNavController().navigate(R.id.toSettings)
    }
}