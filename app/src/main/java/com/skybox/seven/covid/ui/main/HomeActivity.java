package com.skybox.seven.covid.ui.main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.repository.SharedPreferenceRepository;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

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

        navigationView= findViewById(R.id.navbar);
        NavController navController = Navigation.findNavController(this, R.id.container);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.casesFragment, R.id.settingsFragment).build();


        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

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

    @Override
    public void onBeforeLocaleChanged() {
        super.onBeforeLocaleChanged();
    }
}
