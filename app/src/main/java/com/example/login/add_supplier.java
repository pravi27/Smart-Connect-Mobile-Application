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

public class add_supplier extends AppCompatActivity {

    EditText no,name,contact,email,location;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button addSupplier;



    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        mAuth= FirebaseAuth.getInstance();
        no=findViewById(R.id.no);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        location=findViewById(R.id.location);
        contact=findViewById(R.id.contact);
        addSupplier=findViewById(R.id.addSupplier);
        progressBar = findViewById(R.id.progressbar);


        smartconnect= FirebaseDatabase.getInstance().getReference().child("Add_supplier");
        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedatatofirebase();

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Add_supplier").child("Andriod").setValue("abcd");
    }

    private  void updatedatatofirebase() {

        String uno = no.getText().toString();
        String uname = name.getText().toString();
        String uemail = email.getText().toString();
        String ulocation = location.getText().toString();
        String ucontact = contact.getText().toString();





        suppliers suppliers=new suppliers(uno,uname,uemail,ulocation,ucontact);
        smartconnect.push().setValue(suppliers);
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();


    }
}