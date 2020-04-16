package com.skybox.seven.covid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.fragment.HomeFragment;
import com.skybox.seven.covid.ui.fragment.SettingsFragment;

public class HomeActivity extends AppCompatActivity {

    public static final String NAME_MESSAGE = "userName";
    public static final String PHONE_MESSAGE = "userNumber";
    private static FragmentManager fragmentManager;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();

        String userNameM = intent.getStringExtra(NAME_MESSAGE);
        String userNumberM =intent.getStringExtra(PHONE_MESSAGE);


        navigationView= findViewById(R.id.navbar);
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
        switch (menuItem.getItemId()){
            case R.id.home:

                Toast.makeText(HomeActivity.this,"Home Fragment", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, setUpHome(userNameM, userNumberM)).commit();
                break;

            case R.id.settings:
                Toast.makeText(HomeActivity.this, "Settings Fragment", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SettingsFragment()).commit();
                break;

        }
        return false;
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.container, setUpHome(userNameM, userNumberM)).commit();
    }

    private HomeFragment setUpHome(String userNameM, String userNumberM) {
        HomeFragment home = new HomeFragment();
        Bundle homeB = new Bundle();
        homeB.putString("username", userNameM);
        homeB.putString("number", userNumberM);
        home.setArguments(homeB);
        return home;
    }
}
