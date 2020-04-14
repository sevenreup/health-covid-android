package com.skybox.seven.covid.location;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skybox.seven.covid.model.UserLocation;

import io.radar.sdk.Radar;
import io.radar.sdk.RadarTrackingOptions;
import io.radar.sdk.model.RadarEvent;
import io.radar.sdk.model.RadarGeofence;

public class LocationViewModel extends ViewModel {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public LocationViewModel() {
        super();
            configRadar();
    }

    public LiveData<UserLocation> getData() {
        return LocationRepository.instance().getData();
    }

    private void configRadar() {
        RadarTrackingOptions options = RadarTrackingOptions.EFFICIENT;
        options.setSync(RadarTrackingOptions.RadarTrackingOptionsSync.ALL);
        Radar.startTracking(options);
        Radar.setUserId("emulator");
        Radar.trackOnce((radarStatus, location, radarEvents, radarUser) -> {
            StringBuilder ev = new StringBuilder();
            if (radarEvents != null)
                for ( RadarEvent event : radarEvents) {
                    ev.append("{ " + event.toJson().toString() + "}");
                }

            Log.v("Trackonce", "Track once: status = " + radarStatus + "; location = "+ location +" events = "+ ev.toString()+"; user = " + radarUser);
        });
        String[] tem = {"place"};
        Radar.searchGeofences(1000, tem, 10, (radarStatus, location, radarGeofences) -> {
            StringBuilder fence = new StringBuilder();
            if (radarGeofences != null)
                for (RadarGeofence geofence : radarGeofences) {
                    fence.append(geofence.getExternalId());
                }
            Log.v("example", "Search geofences: status = "+radarStatus+"; location = "+location+"; geofences = "+ fence.toString());
        });
    }

}
