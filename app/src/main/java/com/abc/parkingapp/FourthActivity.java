package com.abc.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    private Button Return;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        listview = (ListView)findViewById((R.id.List));
        Return = (Button)findViewById((R.id.btnReturn));

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Spot 1");
        arrayList.add("Spot 2");
        arrayList.add("Spot 3");
        arrayList.add("Spot 4");
        arrayList.add("Spot 5");
        arrayList.add("Spot 6");
        arrayList.add("Spot 7");
        arrayList.add("Spot 8");
        arrayList.add("Spot 9");
        arrayList.add("Spot 10");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listview.setAdapter(arrayAdapter);

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(FourthActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}