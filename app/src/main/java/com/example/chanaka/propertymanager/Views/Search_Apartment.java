package com.example.chanaka.propertymanager.Views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.R.attr.tag;

public class Search_Apartment extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    double latitude;
    double longitude;
    String strAdd;
    EditText txtAddress;
    Property_Handler pHan;
    ArrayList<Property> properties;
    Property[] propertyArray;
    Location l = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__apartment);
        txtAddress = (EditText) findViewById(R.id.txtAddress);

        pHan=new Property_Handler(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMap);

        mapFragment.getMapAsync(this);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        for (int i = 0; i < providers.size(); i++) {
            if (!(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED)) {
                requestPermissions();
            }
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) {
                latitude = l.getLatitude();
                longitude = l.getLongitude();
                updateLoc();

                break;
            }
        }

        Button btnSearch=(Button)findViewById(R.id.btnMapSearch);
        btnSearch.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );
        txtAddress=(EditText)findViewById(R.id.txtAddress);

    }

    private void buttonClicked() {

        searchProperties(txtAddress.getText().toString());
        String[] sProperties=new String[propertyArray.length];
        for(int i=0;i<propertyArray.length;i++){
            sProperties[i]=propertyArray[i].getAddress();
        }
        Intent i=new Intent(getApplicationContext(),ResultApartments.class);
        i.putExtra("properties",sProperties);
        startActivity(i);
    }

    public void searchProperties(String address){
        properties=new ArrayList<Property>();
        DatabaseConnector dbCon=DatabaseConnector.getInstance(getBaseContext());
        properties=dbCon.getNearbyProperties(address);
        propertyArray=new Property[properties.size()];

        for(int i=0;i<properties.size();i++){
            propertyArray[i]=properties.get(i);
        }
        //sort according to ratings
        propertyArray=pHan.sortApartments(propertyArray);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        googleMap=map;
        setUpMap();
    }

    public void setUpMap() {

        final MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title(getCompleteAddressString());

        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

// Moving Camera to a Location with animation
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(15).build();

        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        googleMap.addMarker(marker);

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            requestPermissions();
        }
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                if (marker != null) {
                    googleMap.clear();
                }
                latitude=point.latitude;
                longitude=point.longitude;
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude, longitude)).title(getCompleteAddressString());

                googleMap.addMarker(marker);
                updateLoc();

            }
        });
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);


    }



    private   String getCompleteAddressString() {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null) {
                strAdd = addresses.get(0).getLocality();

            } else {
                //    Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //  Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    public void requestPermissions(){
        ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION },
                tag);
    }


    public void updateLoc(){

        strAdd = getCompleteAddressString();
        txtAddress.setText(strAdd);
    }

}
