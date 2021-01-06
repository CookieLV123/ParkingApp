package com.abc.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private Button Back;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Back = (Button)findViewById((R.id.btnBack));
        logout = (Button)findViewById(R.id.btnLogout);

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
    }
    @Override

    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}