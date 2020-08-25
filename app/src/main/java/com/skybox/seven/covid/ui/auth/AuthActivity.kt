package com.skybox.seven.covid.ui.auth

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.skybox.seven.covid.R

class AuthActivity : AppCompatActivity(), OnLocaleChangedListener {
    private val delegate = LocalizationActivityDelegate(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        delegate.addOnLocaleChangedListener(this)
        delegate.onCreate()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
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
}