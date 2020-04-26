package com.skybox.seven.covid.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.fragment.LoginFragment;
import com.skybox.seven.covid.ui.fragment.RegisterFragment;


public class AuthActivity extends LocalizationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            boolean register = extras.getBoolean("register");
            if (register) {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().add(R.id.mainlayout, registerFragment).commit();
            } else {
                LoginFragment loginFragment = new LoginFragment();
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().add(R.id.mainlayout, loginFragment).commit();
            }
        } else {
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fm = getSupportFragmentManager();

            fm.beginTransaction().add(R.id.mainlayout, loginFragment).commit();
        }
    }
}
