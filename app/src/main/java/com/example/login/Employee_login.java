package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Employee_login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Add_employee");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        final EditText username = findViewById(R.id.loguname);
        final EditText password = findViewById(R.id.logpass);
        final Button login = findViewById(R.id.emp_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Employee_login.this, Emp_home.class);
                startActivity(intent);


            }
        });











    }
}





