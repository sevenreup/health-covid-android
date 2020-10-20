package com.skybox.seven.covid.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.skybox.seven.covid.BuildConfig
import com.skybox.seven.covid.R
import com.skybox.seven.covid.util.Constants
import com.yariksoffice.lingver.Lingver
import kotlin.math.log

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


    override fun onSupportNavigateUp(): Boolean {
        Log.e(TAG, "onSupportNavigateUp: ")
        if (supportFragmentManager.popBackStackImmediate()) {
            return true
        }
        this.onBackPressed()
        return true
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

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            findPreference<Preference>("license")?.setOnPreferenceClickListener {
                startActivity(Intent(context, OssLicensesMenuActivity::class.java))
                true
            }
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
            // todo: fix not attach to context problem
            val i = Intent(requireContext(), HomeActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}

class AboutUsFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)
        view.findViewById<TextView>(R.id.freepik).text = Html.fromHtml("Icons made by <a href=\"https://www.flaticon.com/authors/freepik\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a>")
        view.findViewById<TextView>(R.id.flaticon).text = Html.fromHtml("Vector from <a href=\"https://www.freepik.com/vectors/typography\">Freepik</a>")

        val version = BuildConfig.VERSION_NAME
        view.findViewById<TextView>(R.id.version).text = "Version $version"
        return view
    }
}