package com.abc.parkingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {

    private Button Settings;
    private Button History;

    ArrayList<LatLng> latlng = new ArrayList<LatLng>();
    ArrayList<String> parkingInfo = new ArrayList<String>();
    MapView mapView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFrag.getMapAsync(this);

        LatLng vecppark = new LatLng(57.5376914, 25.41594);
        LatLng vecppark1 = new LatLng(57.028542, 24.098587);
        LatLng vecppark2 = new LatLng(56.789594, 24.572474);
        LatLng vecppark3 = new LatLng(57.177633, 26.048651);

        latlng.add(vecppark);
        latlng.add(vecppark1);
        latlng.add(vecppark2);
        latlng.add(vecppark3);

        parkingInfo.add("Rīga");
        parkingInfo.add("ABD");
        parkingInfo.add("Ogre");
        parkingInfo.add("Jaunpiebalga");

        Settings = (Button)findViewById((R.id.btnSettings));
        History = (Button)findViewById((R.id.btnHistory));

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        for(int i = 0; i<latlng.size();i++){
            for(int j=1; j<parkingInfo.size();j++){
                map.addMarker(new MarkerOptions().position(latlng.get(i))
                        .title(String.valueOf(parkingInfo.get(j))));
                Log.v("TEST", parkingInfo.get(i) +"");
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(latlng.get(i)));
        }
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String title = marker.getTitle();
                AlertDialog dialogBox = new AlertDialog.Builder(SecondActivity.this)
                        .setTitle(title)
                        .show();

                return false;
            }
        });
    }
}