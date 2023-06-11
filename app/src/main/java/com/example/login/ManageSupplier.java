package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageSupplier extends AppCompatActivity {
    Button addSup;
    Button deleteSup;
    Button editSup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_supplier);
        addSup=findViewById(R.id.mebutton);
        addSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSupplier.this, add_supplier.class);
                startActivity(intent);


            }
        });

        deleteSup=findViewById(R.id.ms_button2);
        deleteSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSupplier.this, delete_supplier.class);
                startActivity(intent);


            }
        });

        editSup=findViewById(R.id.ms_button);
        editSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageSupplier.this, update_supplier.class);
                startActivity(intent);


            }
        });


    }

}