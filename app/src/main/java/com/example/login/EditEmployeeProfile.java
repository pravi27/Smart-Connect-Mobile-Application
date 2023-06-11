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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class EditEmployeeProfile extends AppCompatActivity {
    EditText EmpID, username;

    DatabaseReference smartconnect;

    FirebaseAuth mAuth;

    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee_profile);
        mAuth = FirebaseAuth.getInstance();
        EmpID = findViewById(R.id.EmpID);
        username = findViewById(R.id.username);
        Save = findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeID = EmpID.getText().toString().trim();
                updateRecord(employeeID);
            }
        });
    }

    private void updateRecord(String employeeID) {
        smartconnect = FirebaseDatabase.getInstance().getReference("Add_employee");

        // Perform a query to find the record with the matching EmpID
        Query query = smartconnect.orderByChild("empID").equalTo(employeeID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference recordRef = snapshot.getRef();

                    // Update the record with new values
                    recordRef.child("username").setValue(username.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Update successful
                                    System.out.println("Record updated successfully.");

                                    // Redirect to the desired activity
                                    Intent intent = new Intent(EditEmployeeProfile.this, EmployeeMyAccount.class);
                                    startActivity(intent);
                                    finish();
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
