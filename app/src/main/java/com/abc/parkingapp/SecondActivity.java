package com.abc.parkingapp;

import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String SHARED_PREFERENCES = "CAR_PREFERENCES";

    private Button Settings;
    private Button History;
    private Button selectReservation;
    private Button activeReservation;
    DatePicker datePicker;

    String city = "", address = "", owner = "", brand = "", mark = "", licence = "", date = "";

    ArrayList<LatLng> latlng = new ArrayList<LatLng>();
    ArrayList<String> parkingInfo = new ArrayList<String>();
    ArrayList<Marker> markers = new ArrayList<Marker>();
    MapView mapView;
    GoogleMap map;

    public String readPrefs(String value){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);

        String returns = prefs.getString(value, "");

        return returns;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFrag.getMapAsync(this);

        LatLng park_valmiera = new LatLng(57.5376914, 25.41594);
        LatLng park_riga = new LatLng(57.028542, 24.098587);
        LatLng park_ogre = new LatLng(56.789594, 24.572474);
        LatLng park_jaunpiebalga = new LatLng(57.177633, 26.048651);
        LatLng ivmens = new LatLng(57.171498, 26.758768);

        latlng.add(park_valmiera);
        latlng.add(park_riga);
        latlng.add(park_ogre);
        latlng.add(park_jaunpiebalga);
        latlng.add(ivmens);

        parkingInfo.add("Valmiera,Rīgas iela 10,Andris Kuģis,20");
        parkingInfo.add("Rīga,Brīvības gatve 430,Aleksands Anatolijs,50");
        parkingInfo.add("Ogre,Zaļā aleja 13,Ivars Jansons,35");
        parkingInfo.add("Jaunpiebalga,Jaunā iela 1a,Andris Godmanis,10");
        parkingInfo.add("Ivmen's spot,Mana iela 2,Ivis Karpovičs,1 only for Golf IV");

        Settings = (Button) findViewById((R.id.btnSettings));
        History = (Button) findViewById((R.id.btnActiveReserve));
        selectReservation = (Button) findViewById((R.id.btnSelect));
        activeReservation = (Button) findViewById(R.id.btnActiveReserve);
        datePicker = (DatePicker) findViewById(R.id.dateComing);

        activeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (city!="" || address!="" || owner!="") {
                    Intent intent = new Intent(SecondActivity.this, FourthActivity.class);
                    intent.putExtra("CITY", city);
                    intent.putExtra("ADDRESS", address);
                    intent.putExtra("OWNER", owner);
                    intent.putExtra("BRAND", brand);
                    intent.putExtra("MARK", mark);
                    intent.putExtra("LICENCE", licence);
                    //intent.putExtra("DATE", date);

                    startActivity(intent);
                }else {
                    Toast.makeText(SecondActivity.this, "No active reservation", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
        selectReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (city!="" || address!="" || owner!="") {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                    View dialogView = getLayoutInflater().inflate(R.layout.dialog_confirmation, null);
                    EditText carMark = (EditText) dialogView.findViewById(R.id.carMark);
                    EditText carBrand = (EditText) dialogView.findViewById(R.id.carBrand);
                    EditText licenceNr = (EditText) dialogView.findViewById(R.id.licenceNr);
                    DatePicker datePicker = (DatePicker) findViewById(R.id.dateComing);

                    carMark.setText(readPrefs("mark"));
                    carBrand.setText(readPrefs("brand"));
                    licenceNr.setText(readPrefs("licence"));


                    Button confirm = (Button) dialogView.findViewById(R.id.confirm);

                    builder.setView(dialogView);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!carMark.getText().toString().isEmpty() && !carBrand.getText().toString().isEmpty() && !licenceNr.getText().toString().isEmpty()) {
                                mark = carMark.getText().toString();
                                brand = carBrand.getText().toString();
                                licence = licenceNr.getText().toString();

                                SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
                                SharedPreferences.Editor edit = prefs.edit();
                                edit.putString("brand", carBrand.getText().toString());
                                edit.putString("mark", carMark.getText().toString());
                                edit.putString("licence", licenceNr.getText().toString());
                                edit.apply();

                                Toast.makeText(SecondActivity.this, "Reservation completed" + date, Toast.LENGTH_SHORT).show();

                                Log.v("LOG", city + " " + address + " " + owner + " " + mark + " " + brand + " " + licence);
                                dialog.cancel();
                            } else {
                                Toast.makeText(SecondActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                Toast.makeText(SecondActivity.this, "Parking site not selected" + date, Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
        finish();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < latlng.size(); i++) {
            for (int j = 0; j < parkingInfo.size(); j++) {
                String[] temp = (String.valueOf(parkingInfo.get(j))).split(",", 4);
                builder.include((map.addMarker(new MarkerOptions().position(latlng.get(j))
                        .title(temp[0]).snippet("" + j))).getPosition());
                Log.v("TEST", parkingInfo.get(i) + "");
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(latlng.get(i)));

            //the include method will calculate the min and max bound.

            LatLngBounds bounds = builder.build();

            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.20); // offset from edges of the map 10% of screen

            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

            map.animateCamera(cu);
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    String title = marker.getTitle();
                    int i = Integer.parseInt(marker.getSnippet());

                    String[] temp = String.valueOf(parkingInfo.get(i)).split(",", 4);

                    AlertDialog dialogBox = new AlertDialog.Builder(SecondActivity.this)
                            .setTitle(title)
                            .setMessage("Adrese: " + temp[1] + "\nĪpašnieks: " + temp[2] + "\nKopējais vietu skaits: " + temp[3])
                            .setPositiveButton("Select", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button posButton = dialogBox.getButton(AlertDialog.BUTTON_POSITIVE);
                    posButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            city = title;
                            address = temp[1];
                            owner = temp[2];
                            dialogBox.cancel();
                        }
                    });
                    Button negButton = dialogBox.getButton(AlertDialog.BUTTON_NEGATIVE);
                    negButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogBox.cancel();
                        }
                    });

                    return false;
                }
            });
        }
    }

}