package com.skybox.seven.covid.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.skybox.seven.covid.R
import com.skybox.seven.covid.util.Constants
import com.yariksoffice.lingver.Lingver

private const val TAG = "SettingsActivity"
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    class SettingsFragment : PreferenceFragmentCompat() {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences, key: String ->
        when(key) {
            "language_int" -> {
                val locale = (sharedPreferences.getString(key, "0"))?.toInt()
                Log.e(TAG, "$locale")
                context?.let { Lingver.getInstance().setLocale(it, Constants.getLocale(locale!!)) }
                restart()
            }
            else -> {
                Log.e(TAG, "onSharedPreferenceChanged: ass")
            }
        }
    }
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun onResume() {
            super.onResume()
            preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun onPause() {
            super.onPause()
            preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }

        private fun restart() {
            val i = Intent(requireContext(), HomeActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}