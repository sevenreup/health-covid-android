package com.skybox.seven.covid.ui.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.service.GPSListener;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    View mMapView;
    private LocationRequest mLocationRequest;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    GPSListener listener;

    LatLng selectedLoc;

    TextView locNameView;
    LinearLayout searchView;
    TextView searchLocNameView;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        final AutocompleteFilter filter =
                new AutocompleteFilter.Builder().setCountry("MW").build();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMapView = mapFragment.getView();

        Button saveLocation = findViewById(R.id.saveLocation);
        searchView = findViewById(R.id.search);//linear layout containing location name view
        searchLocNameView = findViewById(R.id.searchLocName);
        final EditText descrView = findViewById(R.id.description);
        CardView cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(view -> {

        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(filter).build(LocationActivity.this);
                    startActivityForResult(intent, 0);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        saveLocation.setOnClickListener(v -> {
            Gson gson = new Gson();

            if(selectedLoc != null ){

            }else{
                Toast.makeText(LocationActivity.this,"Please set location", Toast.LENGTH_LONG).show();
            }
        });

        mLocationRequest = LocationRequest.create()
                 .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1000); // 1 second, in milliseconds

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new GPSListener();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request both READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE so that the
            // Pushy SDK will be able to persist the device token in the external storage
            System.out.println("anoooooootthhheeeerrr stage");
            checkPermissions();
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }else{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,0,listener);
        }

    }

    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,
    };

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }else if(p.equals(Manifest.permission.ACCESS_FINE_LOCATION) || p.equals(Manifest.permission.ACCESS_COARSE_LOCATION)){

            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 200);

            return false;
        }else{
            System.out.println("here");
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request both READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE so that the
            // Pushy SDK will be able to persist the device token in the external storage
            System.out.println("Came stage 2");



            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }else{
            Log.d("THIS","Came here thou");
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1000); // 1 second, in milliseconds

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            listener = new GPSListener();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,0,listener);
        }
        System.out.println("Came stage 0");

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);
            System.out.println(place.toString());
            searchLocNameView.setText(place.getName());
            LatLng latLng = place.getLatLng();
            selectedLoc = latLng;
            setMarker(latLng);
        } else if (requestCode == 200) {
            System.out.println("vwvwvvwvevevw");
        }
    }


    public void setMarker(LatLng latLng) {//pick location using marker
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        System.out.println("so wassup");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setMarker(latLng);
                selectedLoc = latLng;
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }else{
            mMap.setMyLocationEnabled(true);

            android.location.Location myLocation = mMap.getMyLocation();
            LatLng latLng = new LatLng(-13.9626,33.7741);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));

            if(mMapView != null && mMapView.findViewById(Integer.parseInt("1")) != null){
                View locationButton = ((View) mMapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
                params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
                params.setMargins(0,240,16,0);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 200){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);

                LatLng latLng = new LatLng(-13.9626,33.7741);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));

            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);

                LatLng latLng = new LatLng(-13.9626,33.7741);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));

                if(mMapView != null && mMapView.findViewById(Integer.parseInt("1")) != null){
                    View locationButton = ((View) mMapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
                    params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
                    params.setMargins(0,240,16,0);
                }
            }

        }
    }

}
