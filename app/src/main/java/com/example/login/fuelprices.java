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

public class fuelprices extends AppCompatActivity {

    TextView view92, view95, viewAD, viewSD;

    DatabaseReference salesRef;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuelprices);
        mAuth = FirebaseAuth.getInstance();
        view92 = findViewById(R.id.View92);
        view95 = findViewById(R.id.View95);
        viewAD = findViewById(R.id.ViewAD);
        viewSD = findViewById(R.id.ViewSD);

        salesRef = FirebaseDatabase.getInstance().getReference().child("Add_Sales").child("-NXOKACEttMEAKn8hUyY");

        salesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String octane92Value = Objects.requireNonNull(dataSnapshot.child("petrol92").getValue(String.class)).trim();
                    String octane95Value = Objects.requireNonNull(dataSnapshot.child("petrol95").getValue(String.class)).trim();
                    String ADieselValue = Objects.requireNonNull(dataSnapshot.child("dieselA").getValue(String.class)).trim();
                    String SDieselValue = Objects.requireNonNull(dataSnapshot.child("dieselS").getValue(String.class)).trim();

                    // Update the TextView fields with the retrieved values
                    view92.setText(octane92Value);
                    view95.setText(octane95Value);
                    viewAD.setText(ADieselValue);
                    viewSD.setText(SDieselValue);

                    Log.d(TAG, "Rs. " + octane92Value);
                    Log.d(TAG, "Rs. " + octane95Value);
                    Log.d(TAG, "Rs. " + ADieselValue);
                    Log.d(TAG, "Rs. " + SDieselValue);
                } else {
                    Log.d(TAG, "No data found in the database");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read the value
                Toast.makeText(fuelprices.this, "Failed to retrieve stock data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
