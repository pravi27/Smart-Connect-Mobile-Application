package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewEmployee extends AppCompatActivity {

    EditText name,nic,contact,EmpID,address,username,password;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button addemp;



    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_employee);
        mAuth=FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        nic=findViewById(R.id.nic);
        contact=findViewById(R.id.contact);
        EmpID=findViewById(R.id.EmpID);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        addemp=findViewById(R.id.addemp);
        progressBar = findViewById(R.id.progressbar);


        smartconnect= FirebaseDatabase.getInstance().getReference().child("Add_employee");
        addemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedatatofirebase();

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Add_employee").child("Andriod").setValue("abcd");
    }

    private  void updatedatatofirebase() {
        String uname = name.getText().toString();
        String unic = nic.getText().toString();
        String ucontact = contact.getText().toString();
        String uempid = EmpID.getText().toString();
        String uaddress = address.getText().toString();
        String uusername = username.getText().toString();
        String upassword = password.getText().toString();

        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(this, "Enter the Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(unic)) {
            Toast.makeText(this, "Enter the NIC number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ucontact)) {
            Toast.makeText(this, "Enter the contact details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(uempid)) {
            Toast.makeText(this, "Enter the Employee ID ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(uusername)) {
            Toast.makeText(this, "Enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(upassword)) {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        employees employees=new employees(uname,unic,ucontact,uempid,uaddress,uusername,upassword);
        smartconnect.push().setValue(employees);
        Toast.makeText(this, "Entered Successfully", Toast.LENGTH_SHORT).show();



    }
}

