package com.skybox.seven.covid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.fragment.main.HomeFragment;
import com.skybox.seven.covid.ui.fragment.SettingsFragment;
import com.skybox.seven.covid.util.BaseModelFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

public class HomeActivity extends AppCompatActivity {

    public static final String NAME_MESSAGE = "userName";
    public static final String PHONE_MESSAGE = "userNumber";
    BottomNavigationView navigationView;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);

        Intent intent = getIntent();
        String userNameM = intent.getStringExtra(NAME_MESSAGE);
        String userNumberM =intent.getStringExtra(PHONE_MESSAGE);
        viewModel.setCredentials(userNameM, userNumberM);
        navigationView= findViewById(R.id.navbar);
        NavigationUI.setupWithNavController(navigationView, Navigation.findNavController(findViewById(R.id.container)));
    }

}
