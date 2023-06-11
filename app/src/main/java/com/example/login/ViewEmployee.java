package com.example.login;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewEmployee extends AppCompatActivity {

    TextView name, nic, contact, address, username, password;

    EditText EmpID;
    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button viewEmpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_employee);
        mAuth = FirebaseAuth.getInstance();
        viewEmpButton = findViewById(R.id.ViewEmp);
        name = findViewById(R.id.name);
        nic = findViewById(R.id.nic);
        contact = findViewById(R.id.contact);
        EmpID = findViewById(R.id.EmpID);
        address = findViewById(R.id.address);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        smartconnect = FirebaseDatabase.getInstance().getReference().child("Add_employee");

        viewEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empID = EmpID.getText().toString().trim();
                viewEmp(empID);
            }
        });
    }

    private void viewEmp(String empID) {
        Query query = smartconnect.orderByChild("empID").equalTo(empID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DataSnapshot nameValue = snapshot.child("name");
                        DataSnapshot nicValue = snapshot.child("nic");
                        DataSnapshot contactValue = snapshot.child("contact");
                        DataSnapshot addressValue = snapshot.child("address");
                        DataSnapshot usernameValue = snapshot.child("username");
                        DataSnapshot passwordValue = snapshot.child("password");

                        name.setText(nameValue.getValue(String.class));
                        nic.setText(nicValue.getValue(String.class));
                        contact.setText(contactValue.getValue(String.class));
                        address.setText(addressValue.getValue(String.class));
                        username.setText(usernameValue.getValue(String.class));
                        password.setText(passwordValue.getValue(String.class));

                        Log.d(TAG, nameValue.getValue(String.class));
                        Log.d(TAG, nicValue.getValue(String.class));
                        Log.d(TAG, contactValue.getValue(String.class));
                        Log.d(TAG, addressValue.getValue(String.class));
                        Log.d(TAG, usernameValue.getValue(String.class));
                        Log.d(TAG, passwordValue.getValue(String.class));
                    }
                } else {
                    Log.d(TAG, "No data found in the database");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read the value
                Toast.makeText(ViewEmployee.this, "Failed to retrieve Employee data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}