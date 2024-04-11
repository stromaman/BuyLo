package com.ecom.buylo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ecom.buylo.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchPageActivity extends AppCompatActivity {

    private MapView mapView;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF000000"));

//        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync((OnMapReadyCallback) SearchPageActivity.this);
//
//        // Handle clicks on the bottom layout
//        findViewById(R.id.bottomLayout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform any actions on bottom layout click
//                Toast.makeText(SearchPageActivity.this, "Bottom Layout Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap map) {
//        googleMap = map;
//
//        // Set up the map with the provided API key
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.getUiSettings().setZoomControlsEnabled(true);
//
//        // Example: Show a marker at a specific location
//        LatLng location = new LatLng(37.7749, -122.4194); // Example: San Francisco, CA
//        googleMap.addMarker(new MarkerOptions().position(location).title("Marker in San Francisco"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
//    }
}