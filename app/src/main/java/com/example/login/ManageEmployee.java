package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageEmployee extends AppCompatActivity {
    Button msadd , msupdate, msdelete, view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_emp);

        msadd=findViewById(R.id.msadd);
        msadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageEmployee.this, AddNewEmployee.class);
                startActivity(intent);

            }
        });
        msdelete=findViewById(R.id.msdelete);
        msdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageEmployee.this, DeleteEmployee.class);
                startActivity(intent);

            }
        });
        msupdate=findViewById(R.id.msupdate);
        msupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageEmployee.this, UpdateEmployee.class);
                startActivity(intent);

            }
        });
        view=findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageEmployee.this, ViewEmployee.class);
                startActivity(intent);

            }
        });
    }
}