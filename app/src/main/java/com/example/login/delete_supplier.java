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

public class delete_supplier extends AppCompatActivity {

    EditText no;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button deleteSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_supplier);
        mAuth = FirebaseAuth.getInstance();
        deleteSupplier = findViewById(R.id.deleteSupplier);
        no = findViewById(R.id.no);

        deleteSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String supplierID = no.getText().toString().trim();
                deleteRecord(supplierID);

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void deleteRecord(String no) {
        smartconnect = FirebaseDatabase.getInstance().getReference("Add_supplier");

        // Perform a query to find the record with the matching EmpID
        Query query = smartconnect.orderByChild("no").equalTo(no);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference recordRef = snapshot.getRef();

                    // Remove the record from the database
                    recordRef.removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Deletion successful
                                    System.out.println("Deleted successfully.");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // An error occurred
                                    System.out.println("Failed to delete record. Error: " + e.getMessage());
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