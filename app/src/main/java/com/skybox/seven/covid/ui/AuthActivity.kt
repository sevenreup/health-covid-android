package com.skybox.seven.covid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivityAuthBinding
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination = if (intent.getBooleanExtra(LOGIN, true)) R.id.registerFragment else R.id.loginFragment
        val fragment = nav_host_fragment as NavHostFragment
        val graphInflater = fragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.auth_navigation)

        navGraph.startDestination = destination
        fragment.navController.graph = navGraph

        binding.close.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val LOGIN = "AUTH_LOGIN"
    }
}