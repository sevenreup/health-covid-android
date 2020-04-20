package com.skybox.seven.covid.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.fragment.LoginFragment;



public class UiTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.mainlayout, loginFragment).commit();
    }
}
