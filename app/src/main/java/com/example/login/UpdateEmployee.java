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

public class UpdateEmployee extends AppCompatActivity {

    EditText name,nic,contact,EmpID,address,username,password;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button updateEmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_employee);
        mAuth = FirebaseAuth.getInstance();
        updateEmp = findViewById(R.id.updateEmp);
        name=findViewById(R.id.name);
        nic=findViewById(R.id.nic);
        contact=findViewById(R.id.contact);
        EmpID=findViewById(R.id.EmpID);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        updateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeID = EmpID.getText().toString().trim();
                updateRecord(employeeID);

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
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

                    // Update the records with new values

                        recordRef.child("name").setValue(name.getText().toString());
                        recordRef.child("nic").setValue(nic.getText().toString());
                        recordRef.child("contact").setValue(contact.getText().toString());
                        recordRef.child("address").setValue(address.getText().toString());
                        recordRef.child("username").setValue(username.getText().toString());
                        recordRef.child("password").setValue(password.getText().toString())

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
