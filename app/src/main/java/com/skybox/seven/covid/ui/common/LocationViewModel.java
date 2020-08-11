package com.skybox.seven.covid.ui.common;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skybox.seven.covid.location.LocationRepository;
import com.skybox.seven.covid.model.UserLocation;

import io.radar.sdk.Radar;
import io.radar.sdk.RadarTrackingOptions;
import io.radar.sdk.model.RadarEvent;
import io.radar.sdk.model.RadarGeofence;

public class LocationViewModel extends ViewModel {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    LocationRepository repository;

    public MutableLiveData<String> currentGeofence = new MutableLiveData<>();

    public LocationViewModel() {
        super();
        configRadar();
        getCurrentGeofence();
    }

    public LiveData<UserLocation> getData() {
        return LocationRepository.instance().getData();
    }

    private void configRadar() {
        RadarTrackingOptions options = RadarTrackingOptions.EFFICIENT;
        options.setSync(RadarTrackingOptions.RadarTrackingOptionsSync.ALL);
        Radar.startTracking(options);
    }

    public void getCurrentGeofence() {

        String[] temp = {"place"};
        Radar.searchGeofences(1000, temp, 10, ((radarStatus, location, radarGeofences) -> {

            StringBuilder fence = new StringBuilder();

            if (radarGeofences != null)
                for (RadarGeofence geofence : radarGeofences) {
                    fence.append(geofence.getDescription());
                }

            currentGeofence.setValue(fence.toString());
        }));
    }

    public void startManualTrack() {
        Radar.trackOnce((radarStatus, location, radarEvents, radarUser) -> {
            StringBuilder ev = new StringBuilder();
            if (radarEvents != null)
                for ( RadarEvent event : radarEvents) {
                    ev.append("{ " + event.toJson().toString() + "}");
                }

            Log.v("Trackonce", "Track once: status = " + radarStatus + "; location = "+ location +" events = "+ ev.toString()+"; user = " + radarUser);
        });
    }

}
