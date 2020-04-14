package com.skybox.seven.covid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skybox.seven.covid.R;

import io.radar.sdk.Radar;
import io.radar.sdk.RadarTrackingOptions;
import io.radar.sdk.model.RadarEvent;
import io.radar.sdk.model.RadarGeofence;

public class RadarTestActivity extends AppCompatActivity {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    TextView playservicesStatus, locationStatus, geofenceStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_test);
        playservicesStatus = findViewById(R.id.playservices);
        locationStatus = findViewById(R.id.location);
        geofenceStatus = findViewById(R.id.geofence);

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
        Radar.trackOnce((radarStatus, location, radarEvents, radarUser) -> {
            StringBuilder ev = new StringBuilder();
            if (radarEvents != null)
                for ( RadarEvent event : radarEvents) {
                    ev.append("{ " + event.toJson().toString() + "}");
                }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (location != null)
                        locationStatus.setText(location.toString());
                    else
                        locationStatus.setText(radarStatus.toString());
                }
            });

            Log.v("Trackonce", "Track once: status = " + radarStatus + "; location = "+ location +" events = "+ ev.toString()+"; user = " + radarUser);
        });

        RadarTrackingOptions options = RadarTrackingOptions.RESPONSIVE;
        options.setSync(RadarTrackingOptions.RadarTrackingOptionsSync.ALL);
        Radar.startTracking(options);

        checkPlayServices();

        Radar.getContext((radarStatus, location, radarContext) -> {
            Log.e("Fence", "onComplete: location: " + location +"; status: " + radarStatus);
        });
        String[] tem = {"places"};
        Radar.searchGeofences(1000, tem, 10, (radarStatus, location, radarGeofences) -> {
            StringBuilder fence = new StringBuilder();
            if (radarGeofences != null)
                for (RadarGeofence geofence : radarGeofences) {
                    fence.append(geofence.getExternalId());
                }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    geofenceStatus.setText("Search geofences: status = "+radarStatus+"; location = "+location+"; geofences = "+ fence.toString());
                }
            });
            Log.v("example", "Search geofences: status = "+radarStatus+"; location = "+location+"; geofences = "+ fence.toString());
        });
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
