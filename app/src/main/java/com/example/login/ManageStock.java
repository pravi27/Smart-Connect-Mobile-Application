package com.example.login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;






public class ManageStock extends AppCompatActivity {


    Button UpdateStock;
    TextView octane92, octane95, ADiesel, SDiesel;

    DatabaseReference priceRef;

    FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stock);

        UpdateStock = findViewById(R.id.updatestock);
        UpdateStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageStock.this, update_stock.class);
                startActivity(intent);
            }
        });


            mAuth = FirebaseAuth.getInstance();
            octane92 = findViewById(R.id.octane92);
            octane95 = findViewById(R.id.octane95);
            ADiesel = findViewById(R.id.ADiesel);
            SDiesel = findViewById(R.id.SDiesel);

            priceRef = FirebaseDatabase.getInstance().getReference().child("Add_stock");

            priceRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String octane92Value = Objects.requireNonNull(dataSnapshot.child("octane92").getValue(String.class)).trim();
                        String octane95Value = Objects.requireNonNull(dataSnapshot.child("octane95").getValue(String.class)).trim();
                        String ADieselValue = Objects.requireNonNull(dataSnapshot.child("ADiesel").getValue(String.class)).trim();
                        String SDieselValue = Objects.requireNonNull(dataSnapshot.child("SDiesel").getValue(String.class)).trim();

                        // Update the TextView fields with the retrieved values
                        octane92.setText(octane92Value);
                        octane95.setText(octane95Value);
                        ADiesel.setText(ADieselValue);
                        SDiesel.setText(SDieselValue);

                        Log.d( TAG,octane92Value); //to print in relevant text values
                        Log.d(TAG, octane95Value);
                        Log.d(TAG,ADieselValue);
                        Log.d( TAG, SDieselValue);
                    } else {
                        Log.d( TAG,"No data found in the database");
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Failed to read the value
                    Toast.makeText(ManageStock.this, "Failed to retrieve stock data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
