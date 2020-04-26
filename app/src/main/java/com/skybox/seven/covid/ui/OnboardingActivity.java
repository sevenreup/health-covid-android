package com.skybox.seven.covid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.skybox.seven.covid.R;

public class OnboardingActivity extends LocalizationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }
}
