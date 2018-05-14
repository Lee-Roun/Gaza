package com.example.jeonwon.gaza;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

//    private GoogleMap mMap;
//    private Button button2;
//    private static final int PLACE_PICKER_REQUEST = 1;
//    private ArrayList<LatLng> arrayPoints = new ArrayList<LatLng>(); // 누른 순서대로 리스트에 저장
//    PolylineOptions polylineOptions;
private ArrayList<LatLng> arrayPoints = new ArrayList<LatLng>(); // 누른 순서대로 리스트에 저장
    PolylineOptions polylineOptions;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MarkerOptions markerOptions = new MarkerOptions();

        // Add a marker
        // in Sydney and move the camera
        LatLng Daegu = new LatLng(35.87222, 128.60250);
        Log.i("Daegu", ""+Daegu.toString());
        markerOptions.position(Daegu).title("대구");
        mMap.addMarker(new MarkerOptions().position(Daegu));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Daegu)); // 어플 시작시 대구에 포커스

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
