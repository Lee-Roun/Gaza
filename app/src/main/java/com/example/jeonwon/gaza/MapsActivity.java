package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public ArrayList<LatLng> arrayPoints = new ArrayList<LatLng>(); // 누른 순서대로 리스트에 저장
    PolylineOptions polylineOptions = new PolylineOptions();

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        polylineOptions.color(Color.RED);
        polylineOptions.width(5);

        Intent intent = getIntent();
        arrayPoints = (ArrayList<LatLng>)intent.getSerializableExtra("point");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MarkerOptions markerOptions = new MarkerOptions();

      //  mMap.addMarker(new MarkerOptions().position(Daegu));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(Daegu)); // 어플 시작시 대구에 포커스


        if(arrayPoints.size() != 0) {
            for (int i = 0; i < arrayPoints.size(); ++i) {
                markerOptions.position(arrayPoints.get(i)).title(i + "");
                mMap.addMarker(markerOptions);

            }
            polylineOptions.addAll(arrayPoints);
            mMap.addPolyline(polylineOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayPoints.get(0)));
        }



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);

                polylineOptions = new PolylineOptions();
                polylineOptions.color(Color.RED);
                polylineOptions.width(5);
                arrayPoints.add(latLng);
                polylineOptions.addAll(arrayPoints);
                mMap.addPolyline(polylineOptions);

            }
        });
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

}
