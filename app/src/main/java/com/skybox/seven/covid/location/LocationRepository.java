package com.skybox.seven.covid.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.skybox.seven.covid.model.UserLocation;

public class LocationRepository {
    private static final LocationRepository INSTANCE = new LocationRepository();
    private final MediatorLiveData<UserLocation> mData = new MediatorLiveData<>();

    private LocationRepository() {}

    public static LocationRepository instance() {
        return INSTANCE;
    }

    public LiveData<UserLocation> getData() {
        return mData;
    }
    public void addDataSource(LiveData<UserLocation> data) {
        mData.addSource(data, mData::setValue);
    }

    public void removeDataSource(LiveData<UserLocation> data) {
        mData.removeSource(data);
    }
}
