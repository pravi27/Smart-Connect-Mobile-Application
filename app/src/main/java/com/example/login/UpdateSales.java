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

public class UpdateSales extends AppCompatActivity {

    EditText Time,Date,pe92,pe95,diA,diS;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button update_f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sales);
        mAuth = FirebaseAuth.getInstance();
        update_f = findViewById(R.id.update_f);
        Time=findViewById(R.id.Time);
        Date=findViewById(R.id.Date);
        pe92=findViewById(R.id.pe92);
        pe95=findViewById(R.id.pe95);
        diA=findViewById(R.id.diA);
        diS=findViewById(R.id.diS);


        update_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeID = Time.getText().toString().trim();
                updateRecord(employeeID);

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void updateRecord(String SaleTime) {
        smartconnect = FirebaseDatabase.getInstance().getReference("Add_Sales");

        // Perform a query to find the record with the matching EmpID
        Query query = smartconnect.orderByChild("time").equalTo(SaleTime);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference recordRef = snapshot.getRef();

                    // Update the records with new values

                    recordRef.child("time").setValue(Time.getText().toString());
                    recordRef.child("date").setValue(Date.getText().toString());
                    recordRef.child("petrol92").setValue(pe92.getText().toString());
                    recordRef.child("petrol95").setValue(pe95.getText().toString());
                    recordRef.child("dieselA").setValue(diA.getText().toString());
                    recordRef.child("dieselS").setValue(diS.getText().toString())


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