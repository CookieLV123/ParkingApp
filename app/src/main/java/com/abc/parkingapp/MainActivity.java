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

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Log.d(TAG, "nu ir galīgā dirsā");
            e.printStackTrace();}

        try  {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://freedb.tech/freedbtech_carparkingapp"
                    , "freedbtech_id15829689_admin", "*Superdrosaparole221*");

            if (conn != null) {
                Log.d(TAG, "ir conn");
            } else {
                Log.d(TAG, "nav conn");
            }

        } catch (SQLException e) {
            Log.d(TAG, "nu ir dirsā");
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
            Log.d(TAG, "nu ir dirsā");
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void validate(String userEmail, String userPassword){
        /*if((userEmail.equals("Admin")) && (userPassword.equals("admin")) && !userEmail.isEmpty() && !userPassword.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }*/
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}