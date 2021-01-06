package com.abc.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Register;
    private Button Login;
    private static final String TAG = "my-activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById((R.id.btnLogin));
        Register = (Button)findViewById((R.id.btnRegister));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });
    }
    @Override

    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void validate(String userEmail, String userPassword){
        /*if((userEmail.equals("Admin")) && (userPassword.equals("admin")) && !userEmail.isEmpty() && !userPassword.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }*/
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}