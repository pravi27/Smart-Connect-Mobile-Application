package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class update_supplier extends AppCompatActivity {

    EditText no, name, contact, email, location;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button updateSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_supplier);
        mAuth = FirebaseAuth.getInstance();
        updateSupplier = findViewById(R.id.updateSupplier);
        no = findViewById(R.id.no);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        location = findViewById(R.id.location);
        contact = findViewById(R.id.contact);

        updateSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String supplierNo = no.getText().toString().trim();
                updateRecord(supplierNo);

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateRecord(String supplierNo) {
        smartconnect = FirebaseDatabase.getInstance().getReference("Add_supplier");

        // Perform a query to find the record with the matching supplierNo
        Query query = smartconnect.orderByChild("no").equalTo(supplierNo);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference recordRef = snapshot.getRef();

                    // Update the record with new values
                    recordRef.child("name").setValue(name.getText().toString());
                    recordRef.child("email").setValue(email.getText().toString());
                    recordRef.child("location").setValue(location.getText().toString());
                    recordRef.child("contact").setValue(contact.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Update successful
                                    System.out.println("Record updated successfully.");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // An error occurred
                                    System.out.println("Failed to update record. Error: " + e.getMessage());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                System.out.println("Failed to query database. Error: " + databaseError.getMessage());
            }
        });
    }
}
