package com.skybox.seven.covid.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.Locale;

public class HomeActivity extends LocalizationActivity {

    public static final String NAME_MESSAGE = "userName";
    public static final String PHONE_MESSAGE = "userNumber";
    BottomNavigationView navigationView;
    MainViewModel viewModel;
    MaterialToolbar toolbar;
    SharedPreferences.OnSharedPreferenceChangeListener changeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create   channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_name);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH));
        }

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d("Notification", "Key: " + key + " Value: " + value);
            }
        }
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = new ViewModelProvider(this, new CovidFactory(getApplication())).get(MainViewModel.class);

        navigationView= findViewById(R.id.navbar);
        NavController navController = Navigation.findNavController(this, R.id.container);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.casesFragment, R.id.settingsFragment).build();


        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

//        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            switch (destination.getId()) {
//                case R.id.homeFragment:
//                    toolbar.setTitle(R.string.app_name);
//                    break;
//                case R.id.settingsFragment:
//                    toolbar.setTitle("Settings");
//                    break;
//                case R.id.casesFragment:
//                    toolbar.setTitle("Covid-19 Stats");
//                    break;
//                default:
//                    toolbar.setTitle("");
//                    break;
//            }
//        });

        changeListener = (sharedPreferences, key) -> {
            if (key.equals(SharedPreferenceRepository.TOKEN)) {
                if (sharedPreferences.getString(SharedPreferenceRepository.TOKEN, null) == null) {
                    recreate();
                }
            }
        };
        viewModel.registerPreferenceChangeListener(changeListener);
        viewModel.changeLanguage.observe(this, this::setLanguage);
    }

    @Override
    protected void onDestroy() {
        viewModel.removePreferenceChangeListener(changeListener);
        super.onDestroy();
    }
}
