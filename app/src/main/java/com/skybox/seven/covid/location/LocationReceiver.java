package com.skybox.seven.covid.location;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.skybox.seven.covid.model.UserLocation;

import org.jetbrains.annotations.NotNull;

import io.radar.sdk.Radar;
import io.radar.sdk.RadarReceiver;
import io.radar.sdk.model.RadarEvent;
import io.radar.sdk.model.RadarUser;

public class LocationReceiver extends RadarReceiver {

    private MediatorLiveData<UserLocation> locationLiveData = new MediatorLiveData<>();

    @Override
    public void onClientLocationUpdated(@NotNull Context context, @NotNull Location location, boolean b, @NotNull Radar.RadarLocationSource radarLocationSource) {
        Log.e("Fence", "onClientLocationUpdated: " + location.toString() + " sourcr: " + radarLocationSource.name());
        locationLiveData.setValue(new UserLocation());
    }

    @Override
    public void onError(@NotNull Context context, @NotNull Radar.RadarStatus radarStatus) {
        Log.e("Fence", "onError: "+ radarStatus.name());
    }

    @Override
    public void onEventsReceived(@NotNull Context context, @NotNull RadarEvent[] radarEvents, @NotNull RadarUser radarUser) {
        for (RadarEvent radarEvent : radarEvents) {
            Log.e("Fence", "onEventsReceived: " + radarEvent.toJson());
        }

    }

    @Override
    public void onLocationUpdated(@NotNull Context context, @NotNull Location location, @NotNull RadarUser radarUser) {
        Log.e("FENCE", "onLocationUpdated: " + location.toString());
    }

    @Override
    public void onLog(@NotNull Context context, @NotNull String s) {
        Log.d("FEnce", "onLog: " + s);
    }

    public LiveData<UserLocation> getData() {
        return locationLiveData;
    }

    void notify(Context context, String body) {

    }
}
