package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageSales extends AppCompatActivity {
    Button add;
    Button update;
    Button report;
    Button Viewdelrep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_sales);

        add=findViewById(R.id.addS);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSales.this, AddSales.class);
                startActivity(intent);

            }
        });

        update=findViewById(R.id.updateS);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSales.this, UpdateSales.class);
                startActivity(intent);

            }
        });

        report=findViewById(R.id.genR);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSales.this, GenerateReport.class);
                startActivity(intent);

            }
        });

        Viewdelrep=findViewById(R.id.viewR);
        Viewdelrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSales.this, ViewReport.class);
                startActivity(intent);

            }
        });

    }
}