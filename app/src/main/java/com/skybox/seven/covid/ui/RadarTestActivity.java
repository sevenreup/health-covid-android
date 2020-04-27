package com.skybox.seven.covid.ui;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.location.LocationReceiver;
import com.skybox.seven.covid.location.LocationRepository;
import com.skybox.seven.covid.viewmodels.LocationViewModel;

public class RadarTestActivity extends LocalizationActivity {
    private LocationReceiver locationReceiver;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    TextView playservicesStatus, locationStatus, geofenceStatus;
    LocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_test);
        if (Build.VERSION.SDK_INT >= 23) {
            int request = 0;
            if(Build.VERSION.SDK_INT >= 29) {
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION};
                ActivityCompat.requestPermissions(this, permissions, request);
            } else {
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(this, permissions, request);
            }
        }
        playservicesStatus = findViewById(R.id.playservices);
        locationStatus = findViewById(R.id.userLocation);
        geofenceStatus = findViewById(R.id.geofence);
        checkPlayServices();
        locationReceiver = new LocationReceiver();
        locationViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LocationViewModel.class);
        locationViewModel.getData().observe(this, userLocation -> geofenceStatus.setText("changed"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocationRepository.instance().addDataSource(locationReceiver.getData());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("io.radar.sdk.RECEIVED");
        registerReceiver(locationReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocationRepository.instance().removeDataSource(locationReceiver.getData());
        unregisterReceiver(locationReceiver);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if(resultCode != ConnectionResult.SUCCESS) {
            if(apiAvailability.isUserResolvableError(resultCode)) {
                playservicesStatus.setText("error, not found");
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("Covid", "This device is not supported.");
                playservicesStatus.setText("not supported");
                finish();
            }
            playservicesStatus.setText("false");
            return false;
        }
        playservicesStatus.setText("is there");
        return true;
    }
}
