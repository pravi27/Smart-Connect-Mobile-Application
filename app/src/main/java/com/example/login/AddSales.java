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

public class AddSales extends AppCompatActivity {

    EditText editTextTime,editTextDate,petrol92,petrol95,dieselA,dieselS;

    DatabaseReference smartconnect;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Button generate_rep;



    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        mAuth=FirebaseAuth.getInstance();
        editTextTime=findViewById(R.id.editTextTime);
        editTextDate=findViewById(R.id.editTextDate);
        petrol92=findViewById(R.id.petrol92);
        petrol95=findViewById(R.id.petrol95);
        dieselA=findViewById(R.id.dieselA);
        dieselS=findViewById(R.id.dieselS);
        generate_rep=findViewById(R.id.generate_rep);
        progressBar = findViewById(R.id.progressbar);

        smartconnect= FirebaseDatabase.getInstance().getReference().child("Add_Sales");
        generate_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddatatofirebase();

                Intent intent = new Intent(getApplicationContext(), Manager_home.class);
                startActivity(intent);
                finish();
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Add_Sales").child("Andriod").setValue("abcd");
    }
    private  void adddatatofirebase() {
        String time = editTextTime.getText().toString();
        String date = editTextDate.getText().toString();
        String petrol_92 = petrol92.getText().toString();
        String petrol_95 = petrol95.getText().toString();
        String diesel_A = dieselA.getText().toString();
        String diesel_S = dieselS.getText().toString();

        if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "Enter the Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "Enter the NIC number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(petrol_92)) {
            Toast.makeText(this, "Enter the contact details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(petrol_95)) {
            Toast.makeText(this, "Enter the Employee ID ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(diesel_A)) {
            Toast.makeText(this, "Enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(diesel_S)) {
            Toast.makeText(this, "Enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        Sales Sales=new Sales(time,date,petrol_95,petrol_95,diesel_A,diesel_S);
        smartconnect.push().setValue(Sales);
        Toast.makeText(this, "Entered Successfully", Toast.LENGTH_SHORT).show();


    }
}
