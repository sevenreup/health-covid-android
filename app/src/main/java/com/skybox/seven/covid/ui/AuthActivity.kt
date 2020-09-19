package com.skybox.seven.covid.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivityAuthBinding
import com.skybox.seven.covid.ui.auth.AuthViewModel
import com.skybox.seven.covid.ui.auth.RegisterViewModel
import com.skybox.seven.covid.work.TokenWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_auth.*

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    val viewModel: AuthViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination = if (intent.getBooleanExtra(LOGIN, true)) R.id.loginFragment else R.id.registerFragment
        val fragment = nav_host_fragment as NavHostFragment
        val graphInflater = fragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.auth_navigation)

        navGraph.startDestination = destination
        fragment.navController.graph = navGraph

        binding.close.setOnClickListener {
            finish()
        }

        viewModel.firebaseToken.observe(this, Observer {
            val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            val worker = OneTimeWorkRequest.Builder(TokenWorker::class.java)
                    .setConstraints(constraints)
                    .build()
            WorkManager.getInstance(this).enqueue(worker)
            Toast.makeText(this, R.string.login, Toast.LENGTH_LONG).show()
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        })

        registerViewModel.isRegistered.observe(this, Observer {
            if (it) {
                MaterialAlertDialogBuilder(this)
                        .setTitle(resources.getString(R.string.logged_in_title))
                        .setMessage(resources.getString(R.string.logged_in_text))
                        .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                            findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
                        }
                        .show()
            }
        })
    }

    companion object {
        const val LOGIN = "AUTH_LOGIN"
    }
}