package com.abc.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FourthActivity extends SecondActivity {

    private Button Return;
    private Button deleteReservation;
    TextView city, address, owner, brand, mark, licence, date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        SecondActivity sa = new SecondActivity();
        String cityS = getIntent().getStringExtra("CITY");
        String addressS = getIntent().getStringExtra("ADDRESS");
        String ownerS = getIntent().getStringExtra("OWNER");
        String brandS = getIntent().getStringExtra("BRAND");
        String markS = getIntent().getStringExtra("MARK");
        String licenceS = getIntent().getStringExtra("LICENCE");
        //String dateS = getIntent().getStringExtra("DATE");

        city = (TextView) findViewById(R.id.city);
        address = (TextView) findViewById(R.id.address);
        owner = (TextView) findViewById(R.id.owner);
        brand = (TextView) findViewById(R.id.brand);
        mark = (TextView) findViewById(R.id.mark);
        licence = (TextView) findViewById(R.id.licence);
        //date = (TextView)findViewById(R.id.date);

        city.setText(cityS);
        address.setText(addressS);
        owner.setText(ownerS);
        brand.setText(brandS);
        mark.setText(markS);
        licence.setText(licenceS);
        //date.setText(dateS);

        Return = (Button) findViewById((R.id.btnReturn));
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        deleteReservation = (Button) findViewById((R.id.btnDeleteReserv));
        deleteReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city.setText("");
                address.setText("");
                owner.setText("");
                brand.setText("");
                mark.setText("");
                licence.setText("");
                Toast.makeText(FourthActivity.this, "Reservation deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FourthActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
