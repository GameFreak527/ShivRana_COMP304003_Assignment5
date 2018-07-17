package com.example.shivrana.shivrana_comp304_003_assignment5;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String placeName;
    String Brand;
    String locationDetails;
    double longitude;
    ImageView logo;
    TextView details,locationTitle;
    double latitude;
    Button satelite,map;
    private GoogleMap mMap;
    Geocoder locationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //User defined

        //declaration
        declaration();

        //Button onClick Events
        btnEvents(satelite,map);
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

        locationDetails();
        LatLng selectLocation = new LatLng(latitude,longitude);

        //Creating a Custom Marker
        customMarker();

        //Adding marker into the Map
        mMap.addMarker(new MarkerOptions().position(selectLocation));

        //Zoom into the marker
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectLocation,60));

        //Moves camera to the desired location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(selectLocation));

        //Animated Zoom in
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(latitude,longitude))
                        .zoom(20)
                        .build()));
            }
        });

    }

    public void customMarker(){
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View view = getLayoutInflater().inflate(R.layout.marker_custom,null);

                logo = view.findViewById(R.id.companyLogo);
                details = view.findViewById(R.id.locationDetails);
                locationTitle = view.findViewById(R.id.locationTitle);

                //populating the Window components
               details.setText(locationDetails);
               locationTitle.setText(placeName);
               logoSelection(logo);

                return view;
            }
        });
    }

    public void btnEvents(Button satelite,Button map){
        satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                Toast.makeText(MapsActivity.this, "Satelite Mode", Toast.LENGTH_SHORT).show();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                Toast.makeText(MapsActivity.this, "Map Mode", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void declaration(){
        SharedPreferences myPref = getSharedPreferences("myData", MODE_PRIVATE);
        Brand = myPref.getString("Brand","");
        satelite = findViewById(R.id.sateliteView);
        map = findViewById(R.id.mapView);
        placeName = getIntent().getStringExtra("ShowRoom");
        locationInfo = new Geocoder(getApplicationContext());
    }

    public void locationDetails(){
        try{
            List<Address> locationResult = locationInfo.getFromLocationName(placeName,1);
            Iterator<Address> locations = locationResult.iterator();

            while (locations.hasNext()){
                Address nameLoc = locations.next();
                latitude = nameLoc.getLatitude();
                longitude = nameLoc.getLongitude();
                locationDetails = String.format(" %s, %s\n %s\n %s",nameLoc.getSubThoroughfare(),nameLoc.getThoroughfare(),nameLoc.getLocality(),nameLoc.getPostalCode());

                break;
            }
        } catch (IOException e) {
            Log.e("GeoAddress","Failed to get location info",e);
        }
    }

    public void logoSelection(ImageView img){
        switch (Brand){
            case "Ford":
                img.setBackground(getResources().getDrawable(R.drawable.ford));
                break;
            case "Toyota":
                img.setBackground(getResources().getDrawable(R.drawable.toyota));
                break;
            case "Honda":
                img.setBackground(getResources().getDrawable(R.drawable.honda));
                break;
            case "Nissan":
                img.setBackground(getResources().getDrawable(R.drawable.nissan));
                break;
            case "Chevrolet":
                img.setBackground(getResources().getDrawable(R.drawable.chevrolet));
                break;
                default:

        }
    }
}
