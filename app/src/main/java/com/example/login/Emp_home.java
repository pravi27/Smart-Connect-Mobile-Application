package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Emp_home extends AppCompatActivity {
    Button qr;
    Button mprofile;

    Button fuel_rec;
    Button fuel_stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_home);
        qr=findViewById(R.id.mebutton);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_home.this, emp_scan_qr.class);
                startActivity(intent);


            }
        });

        mprofile=findViewById(R.id.mprofile_button);
        mprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_home.this, EmployeeMyAccount.class);
                startActivity(intent);


            }
        });

        fuel_rec=findViewById(R.id.fuel_rec);
        fuel_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_home.this, EnterFuelRecord.class);
                startActivity(intent);


            }
        });

        fuel_stock=findViewById(R.id.m_stock);
        fuel_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_home.this, fuel_stock.class);
                startActivity(intent);


            }
        });
    }
}