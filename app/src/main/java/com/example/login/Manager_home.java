package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager_home extends AppCompatActivity {
    Button managesales;
    Button manageemp;
    Button managesup;
    Button managestocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        managesales=findViewById(R.id.msales_button);
        managesales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_home.this, ManageSales.class);
                startActivity(intent);


            }
        });

        manageemp=findViewById(R.id.meempbutton);
        manageemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_home.this, ManageEmployee.class);
                startActivity(intent);


            }
        });

        managesup=findViewById(R.id.m_sup_button);
        managesup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_home.this, ManageSupplier.class);
                startActivity(intent);


            }
        });

        managestocks=findViewById(R.id.m_stock);
        managestocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager_home.this, ManageStock.class);
                startActivity(intent);


            }
        });
    }
}