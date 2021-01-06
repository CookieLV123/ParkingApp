package com.abc.parkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private Button Back;
    private Button logout;
    private Button Dark;
    private int mLastDayNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().setTitle("Settings");

        Back = (Button)findViewById((R.id.btnBack));
        logout = (Button)findViewById(R.id.btnLogout);
        Dark = (Button)findViewById(R.id.btnDarkMode);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });
        Dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Dark.setText("Light Mode");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

    }
    @Override

    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}