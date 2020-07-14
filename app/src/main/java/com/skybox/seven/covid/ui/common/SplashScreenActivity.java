package com.skybox.seven.covid.ui.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.main.HomeActivity;
import com.skybox.seven.covid.ui.onboarding.OnBoardingActivity;

import static com.skybox.seven.covid.repository.SharedPreferenceRepository.ONBOARDING;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(getApplication().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean(ONBOARDING, false)) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finishAffinity();
        } else {
            Intent intent = new Intent(this, OnBoardingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finishAffinity();
        }
    }
}
