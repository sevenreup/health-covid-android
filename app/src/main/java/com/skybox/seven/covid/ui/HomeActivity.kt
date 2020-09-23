package com.skybox.seven.covid.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivityHomeBinding
import com.skybox.seven.covid.util.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create   channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_name)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH))
        }
        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!![key]
                Log.d("Notification", "Key: $key Value: $value")
            }
        }

        if (savedInstanceState == null) {
            setUpBottomNavigation()
        }
        viewModel.setUpWorker(this)
    }

    private fun setUpBottomNavigation() {
        val navGraphIDs = listOf(R.navigation.home, R.navigation.stats, R.navigation.profile)

        val controllers = binding.bottomNav.setupWithNavController(
                navGraphIds = navGraphIDs,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_fragment,
                intent = intent
        )
        currentNavController = controllers
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setUpBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (viewModel.searchOpened.value!!) {
            viewModel.closeSearch.value = true
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val NAME_MESSAGE = "userName"
        const val PHONE_MESSAGE = "userNumber"
    }
}