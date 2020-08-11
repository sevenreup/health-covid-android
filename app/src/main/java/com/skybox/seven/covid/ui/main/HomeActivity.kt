package com.skybox.seven.covid.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivityHomeBinding
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import com.skybox.seven.covid.util.setupWithNavController
import com.skybox.seven.covid.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), OnLocaleChangedListener {
    private val delegate = LocalizationActivityDelegate(this)

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private var changeListener: OnSharedPreferenceChangeListener? = null
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        delegate.addOnLocaleChangedListener(this)
        delegate.onCreate()
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create   channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_name)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH))
        }
        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!![key]
                Log.d("Notification", "Key: $key Value: $value")
            }
        }
        setSupportActionBar(binding.toolbar)

        changeListener = OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences, key: String ->
            if (key == SharedPreferenceRepository.TOKEN) {
                if (sharedPreferences.getString(SharedPreferenceRepository.TOKEN, null) == null) {
                    recreate()
                }
            }
        }
        viewModel!!.registerPreferenceChangeListener(changeListener)
        viewModel!!.changeLanguage.observe(this, Observer { locale: Locale? -> delegate.setLanguage(this, locale!!) })

        if (savedInstanceState == null) {
            setUpBottomNavigation()
        }
    }

    private fun setUpBottomNavigation() {
        val navGraphIDs = listOf(R.navigation.home, R.navigation.stats, R.navigation.news, R.navigation.profile)

        val controllers = binding.bottomNav.setupWithNavController(
                navGraphIds = navGraphIDs,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_fragment,
                intent = intent
        )
        controllers.observe(this, Observer {
            setupActionBarWithNavController(it)
        })
        currentNavController = controllers
    }

    override fun onDestroy() {
        viewModel!!.removePreferenceChangeListener(changeListener)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        delegate.onResume(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(delegate.attachBaseContext(newBase))
    }

    override fun getApplicationContext(): Context {
        return delegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return delegate.getResources(super.getResources())
    }

    override fun onAfterLocaleChanged() {}
    override fun onBeforeLocaleChanged() {}

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

    companion object {
        const val NAME_MESSAGE = "userName"
        const val PHONE_MESSAGE = "userNumber"
    }
}