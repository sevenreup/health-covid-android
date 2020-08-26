package com.skybox.seven.covid

import android.app.Application
import android.content.Context
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.skybox.seven.covid.data.entities.Language
import com.yariksoffice.lingver.Lingver
import com.yariksoffice.lingver.store.PreferenceLocaleStore
import dagger.hilt.android.HiltAndroidApp
import io.radar.sdk.Radar.RadarLogLevel
import io.radar.sdk.Radar.initialize
import io.radar.sdk.Radar.setLogLevel
import java.util.*

@HiltAndroidApp
class Covid : Application() {
    override fun onCreate() {
        super.onCreate()
        initialize(this, "prj_test_pk_7f75cb504e6e5d6262cc75012cdf1413abe8875e")
        setLogLevel(RadarLogLevel.DEBUG)
        val store = PreferenceLocaleStore(this, Locale(LANGUAGE_ENGLISH))
        val lingver = Lingver.init(this, store)
    }

    companion object {
        val SUPPORTED_LOCALES = Arrays.asList(
                Language(1, "en", "US", "English"),
                Language(2, "ny", "MW", "Chichewa")
        )
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ENGLISH_COUNTRY = "US"
        const val LANGUAGE_CHICHEWA = "ny"
        const val LANGUAGE_CHICHEWA_COUNTRY = "MW"
    }
}