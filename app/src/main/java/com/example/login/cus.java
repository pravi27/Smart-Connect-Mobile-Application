package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class cus extends AppCompatActivity {
    Button myprofile;
    Button fuelstation;
    Button qr;
    Button fuel;

    FirebaseAuth auth;

    FirebaseUser user;

    Button lout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        auth = FirebaseAuth.getInstance();
        lout = findViewById(R.id.logout);
        user = auth.getCurrentUser();

        myprofile=findViewById(R.id.My_profile);
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cus.this, Myprofile_Cust.class);
                startActivity(intent);


            }
        });


        lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(cus.this, Login.class);
                startActivity(intent);
                finish();
            }
        });





        fuelstation=findViewById(R.id.btn_map_s);
        fuelstation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cus.this, cust_map.class);
                startActivity(intent);


            }
        });

        fuel=findViewById(R.id.prices);
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cus.this, fuelprices.class);
                startActivity(intent);


            }
        });

        qr=findViewById(R.id.QR);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cus.this, MyQR.class);
                startActivity(intent);


            }
        });
    }
}