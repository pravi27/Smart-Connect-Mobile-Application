package com.example.login;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
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

public class fuel_stock extends AppCompatActivity {

    TextView view92, view95, viewAD, viewSD;

    DatabaseReference stockRef;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_stock);
        mAuth = FirebaseAuth.getInstance();
        view92 = findViewById(R.id.view92);
        view95 = findViewById(R.id.view95);
        viewAD = findViewById(R.id.viewAD);
        viewSD = findViewById(R.id.viewSD);

        stockRef = FirebaseDatabase.getInstance().getReference().child("Add_stock");

        stockRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String octane92Value = Objects.requireNonNull(dataSnapshot.child("octane92").getValue(String.class)).trim();
                    String octane95Value = Objects.requireNonNull(dataSnapshot.child("octane95").getValue(String.class)).trim();
                    String ADieselValue = Objects.requireNonNull(dataSnapshot.child("ADiesel").getValue(String.class)).trim();
                    String SDieselValue = Objects.requireNonNull(dataSnapshot.child("SDiesel").getValue(String.class)).trim();

                    // Update the TextView fields with the retrieved values
                    view92.setText(octane92Value);
                    view95.setText(octane95Value);
                    viewAD.setText(ADieselValue);
                    viewSD.setText(SDieselValue);

                    Log.d(TAG, octane92Value);
                    Log.d(TAG, octane95Value);
                    Log.d(TAG, ADieselValue);
                    Log.d(TAG, SDieselValue);
                } else {
                    Log.d(TAG, "No data found in the database");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read the value
                Toast.makeText(fuel_stock.this, "Failed to retrieve stock data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
