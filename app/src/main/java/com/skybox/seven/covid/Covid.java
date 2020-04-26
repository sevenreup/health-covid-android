package com.skybox.seven.covid;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import io.radar.sdk.Radar;

public class Covid extends Application {
    private LocalizationApplicationDelegate localizationApplicationDelegate = new LocalizationApplicationDelegate();
    public static final List<Locale> SUPPORTED_LOCALES =
            Arrays.asList(
                    new Locale("en", "US"),
                    new Locale("ny", "MW")
            );
    @Override
    public void onCreate() {
        super.onCreate();
        Radar.initialize(this, "prj_test_pk_7f75cb504e6e5d6262cc75012cdf1413abe8875e");
        Radar.setLogLevel(Radar.RadarLogLevel.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        localizationApplicationDelegate.setDefaultLanguage(base, Locale.ENGLISH);
        super.attachBaseContext(localizationApplicationDelegate.attachBaseContext(base));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localizationApplicationDelegate.onConfigurationChanged(this);
    }

    @Override
    public Context getApplicationContext() {
        return localizationApplicationDelegate.getApplicationContext(super.getApplicationContext());
    }
}
