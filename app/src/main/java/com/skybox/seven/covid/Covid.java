package com.skybox.seven.covid;

import android.app.Application;

import io.radar.sdk.Radar;

public class Covid extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Radar.initialize(this, "prj_test_pk_7f75cb504e6e5d6262cc75012cdf1413abe8875e");
        Radar.setLogLevel(Radar.RadarLogLevel.DEBUG);
    }
}
