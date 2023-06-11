package com.example.login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class GenerateReport extends AppCompatActivity {

    TextView pe92, pe95, diA, diS;
    EditText Time, Date, income, expense;
    DatabaseReference priceRef, reportRef;

    FirebaseAuth mAuth;
    Button generate_rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        mAuth = FirebaseAuth.getInstance();
        pe92 = findViewById(R.id.pe92);
        pe95 = findViewById(R.id.pe95);
        diA = findViewById(R.id.diA);
        diS = findViewById(R.id.diS);
        Time = findViewById(R.id.Time);
        Date = findViewById(R.id.Date);
        income = findViewById(R.id.income);
        expense = findViewById(R.id.expense);
        generate_rep = findViewById(R.id.generate_rep);

        priceRef = FirebaseDatabase.getInstance().getReference().child("Add_Sales").child("-NXOKACEttMEAKn8hUyY");
        reportRef = FirebaseDatabase.getInstance().getReference().child("Add_Report");

        priceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String octane92Value = Objects.requireNonNull(dataSnapshot.child("petrol92").getValue(String.class)).trim();
                    String octane95Value = Objects.requireNonNull(dataSnapshot.child("petrol95").getValue(String.class)).trim();
                    String ADieselValue = Objects.requireNonNull(dataSnapshot.child("dieselA").getValue(String.class)).trim();
                    String SDieselValue = Objects.requireNonNull(dataSnapshot.child("dieselS").getValue(String.class)).trim();

                    // Update the TextView fields with the retrieved values
                    pe92.setText(octane92Value);
                    pe95.setText(octane95Value);
                    diA.setText(ADieselValue);
                    diS.setText(SDieselValue);

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
                Toast.makeText(GenerateReport.this, "Failed to retrieve stock data", Toast.LENGTH_SHORT).show();
            }
        });

        generate_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToFirebase();
                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addDataToFirebase() {
        String time = Time.getText().toString();
        String date = Date.getText().toString();
        String Income = income.getText().toString();
        String Expense = expense.getText().toString();

        if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "Enter the Time", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "Enter the Date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Income)) {
            Toast.makeText(this, "Enter the Income", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Expense)) {
            Toast.makeText(this, "Enter the Expenditure ", Toast.LENGTH_SHORT).show();
            return;
        }

        Report report = new Report(time, date, Income, Expense);
        reportRef.push().setValue(report);
        Toast.makeText(this, "Entered Successfully", Toast.LENGTH_SHORT).show();
    }
}
