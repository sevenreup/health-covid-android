package com.skybox.seven.covid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import android.widget.Toast;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.fragment.LoginFragment;
import com.skybox.seven.covid.ui.fragment.RegisterFragment;



public class UiTestActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.mainlayout, loginFragment).commit();
    }
}
