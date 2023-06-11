package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterFuelRecord extends AppCompatActivity {


    EditText time,date,price,units;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button EnterR;


    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_fuel_record);
        mAuth= FirebaseAuth.getInstance();
        time=findViewById(R.id.editTextTime);
        date=findViewById(R.id.editTextDate);
        price=findViewById(R.id.editTextPrice);
        units=findViewById(R.id.editTextUnits);
        EnterR=findViewById(R.id.EnterR);
        progressBar = findViewById(R.id.progressbar);

        smartconnect= FirebaseDatabase.getInstance().getReference().child("Add_records");
        EnterR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updatedatatofirebase();

                Intent intent = new Intent(getApplicationContext(), Emp_home.class);
                startActivity(intent);
                finish();
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Add_records").child("Andriod").setValue("abcd");
    }

    private  void updatedatatofirebase() {
        String Time = time.getText().toString();
        String Date = date.getText().toString();
        String Price = price.getText().toString();
        String Units = units.getText().toString();

        records records;
        records = new records(Time,Date,Price,Units);
        smartconnect.push().setValue(records);
        Toast.makeText(this, "Entered Successfully", Toast.LENGTH_SHORT).show();

    }
}