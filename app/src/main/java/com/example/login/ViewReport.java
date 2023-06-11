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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewReport extends AppCompatActivity {

    TextView pe92, pe95, diA, diS, Time, Date, income, expense;

    DatabaseReference salesRef, reportRef;

    FirebaseAuth mAuth;

    Button viewR, deleteR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        mAuth = FirebaseAuth.getInstance();
        pe92 = findViewById(R.id.pe92);
        pe95 = findViewById(R.id.pe95);
        diA = findViewById(R.id.diA);
        diS = findViewById(R.id.diS);
        Time = findViewById(R.id.Time);
        Date = findViewById(R.id.Date);
        income = findViewById(R.id.income);
        expense = findViewById(R.id.expense);
        viewR = findViewById(R.id.viewR);
        deleteR = findViewById(R.id.deleteR);

        viewR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = Date.getText().toString().trim();
                viewReport(date);
                viewSReport(date);
            }
        });

        salesRef = FirebaseDatabase.getInstance().getReference().child("Add_Sales");

        deleteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deletebydate = Date.getText().toString().trim();
                deleteRecord(deletebydate);

                Intent intent = new Intent(getApplicationContext(), ManageSales.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void viewSReport(String date) {
        salesRef = FirebaseDatabase.getInstance().getReference("Add_Sales");
        // Perform a query to find the record with the matching date
        Query query = salesRef.orderByChild("date").equalTo(date);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference salesRef = snapshot.getRef();

                    salesRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                DataSnapshot petrol92Snapshot = dataSnapshot.child("petrol92");
                                DataSnapshot petrol95Snapshot = dataSnapshot.child("petrol95");
                                DataSnapshot dieselASnapshot = dataSnapshot.child("dieselA");
                                DataSnapshot dieselSSnapshot = dataSnapshot.child("dieselS");

                                if (petrol92Snapshot.exists()) {
                                    String octane92Value = petrol92Snapshot.getValue(String.class);
                                    if (octane92Value != null) {
                                        octane92Value = octane92Value.trim();
                                        pe92.setText(octane92Value);
                                        Log.d(TAG, octane92Value);
                                    } else {
                                        Log.d(TAG, "petrol92 value is null");
                                    }
                                } else {
                                    Log.d(TAG, "petrol92 snapshot does not exist");
                                }

                                if (petrol95Snapshot.exists()) {
                                    String octane95Value = petrol95Snapshot.getValue(String.class);
                                    if (octane95Value != null) {
                                        octane95Value = octane95Value.trim();
                                        pe95.setText(octane95Value);
                                        Log.d(TAG, octane95Value);
                                    } else {
                                        Log.d(TAG, "petrol95 value is null");
                                    }
                                } else {
                                    Log.d(TAG, "petrol95 snapshot does not exist");
                                }

                                if (dieselASnapshot.exists()) {
                                    String ADieselValue = dieselASnapshot.getValue(String.class);
                                    if (ADieselValue != null) {
                                        ADieselValue = ADieselValue.trim();
                                        diA.setText(ADieselValue);
                                        Log.d(TAG, ADieselValue);
                                    } else {
                                        Log.d(TAG, "dieselA value is null");
                                    }
                                } else {
                                    Log.d(TAG, "dieselA snapshot does not exist");
                                }

                                if (dieselSSnapshot.exists()) {
                                    String SDieselValue = dieselSSnapshot.getValue(String.class);
                                    if (SDieselValue != null) {
                                        SDieselValue = SDieselValue.trim();
                                        diS.setText(SDieselValue);
                                        Log.d(TAG, SDieselValue);
                                    } else {
                                        Log.d(TAG, "dieselS value is null");
                                    }
                                } else {
                                    Log.d(TAG, "dieselS snapshot does not exist");
                                }

                            } else {
                                Log.d(TAG, "No data found in the database");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Failed to read the value
                            Toast.makeText(ViewReport.this, "Failed to retrieve stock data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void viewReport(String date) {
        reportRef = FirebaseDatabase.getInstance().getReference("Add_Report");
        // Perform a query to find the record with the matching date
        Query query = reportRef.orderByChild("date").equalTo(date);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference reportRef = snapshot.getRef();

                    reportRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String viewTime = dataSnapshot.child("time").getValue(String.class);
                                String viewIncome = dataSnapshot.child("income").getValue(String.class);
                                String viewExpense = dataSnapshot.child("expense").getValue(String.class);

                                if (viewTime != null) {
                                    viewTime = viewTime.trim();
                                    Time.setText(viewTime);
                                    Log.d(TAG, viewTime);
                                } else {
                                    Log.d(TAG, "viewTime value is null");
                                }

                                if (viewIncome != null) {
                                    viewIncome = viewIncome.trim();
                                    income.setText(viewIncome);
                                    Log.d(TAG, viewIncome);
                                } else {
                                    Log.d(TAG, "viewIncome value is null");
                                }

                                if (viewExpense != null) {
                                    viewExpense = viewExpense.trim();
                                    expense.setText(viewExpense);
                                    Log.d(TAG, viewExpense);
                                } else {
                                    Log.d(TAG, "viewExpense value is null");
                                }

                            } else {
                                Log.d(TAG, "No data found in the database");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Failed to read the value
                            Toast.makeText(ViewReport.this, "Failed to retrieve stock data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void deleteRecord(String date) {
        reportRef = FirebaseDatabase.getInstance().getReference("Add_Report");

        // Perform a query to find the record with the matching EmpID
        Query query = reportRef.orderByChild("date").equalTo(date);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the reference to the specific record
                    DatabaseReference reportRef = snapshot.getRef();

                    // Remove the record from the database
                    reportRef.removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Deletion successful
                                    Toast.makeText(ViewReport.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Record deleted successfully");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // An error occurred
                                    Toast.makeText(ViewReport.this, "Failed to delete record. Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Failed to delete record. Error: " + e.getMessage());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Toast.makeText(ViewReport.this, "Failed to query database. Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failed to query database. Error: " + databaseError.getMessage());
            }
        });
    }
}