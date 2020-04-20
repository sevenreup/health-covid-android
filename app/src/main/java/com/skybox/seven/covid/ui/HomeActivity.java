package com.skybox.seven.covid.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.viewmodels.MainViewModel;

public class HomeActivity extends AppCompatActivity {

    public static final String NAME_MESSAGE = "userName";
    public static final String PHONE_MESSAGE = "userNumber";
    BottomNavigationView navigationView;
    MainViewModel viewModel;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);


        Intent intent = getIntent();
        String userNameM = intent.getStringExtra(NAME_MESSAGE);
        String userNumberM =intent.getStringExtra(PHONE_MESSAGE);
        viewModel.setCredentials(userNameM, userNumberM);

        navigationView= findViewById(R.id.navbar);
        NavController navController = Navigation.findNavController(this, R.id.container);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.casesFragment, R.id.settingsFragment).build();


        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.homeFragment:
                    toolbar.setTitle(R.string.app_name);
                    break;
                case R.id.settingsFragment:
                    toolbar.setTitle("Settings");
                    break;
                case R.id.casesFragment:
                    toolbar.setTitle("Covid-19 Stats");
                    break;
                default:
                    toolbar.setTitle("");
                    break;
            }
        });
    }
}
