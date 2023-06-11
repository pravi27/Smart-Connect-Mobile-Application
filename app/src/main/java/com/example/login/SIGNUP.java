package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SIGNUP extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://smart-connect-b162d-default-rtdb.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText name = findViewById(R.id.name);
        final EditText nic = findViewById(R.id.nic);
        final EditText contact = findViewById(R.id.contact);
        final EditText Vec = findViewById(R.id.vehical);
        final EditText Address = findViewById(R.id.address);
        final EditText username = findViewById(R.id.usernameee);
        final EditText password = findViewById(R.id.passworddd);

        final Button singupbtn = findViewById(R.id.signup);
        final Button loginbtn = findViewById(R.id.login);

        singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from Edittext
                final String nametxt = name.getText().toString();
                final String nictxt = nic.getText().toString();
                final String contacttxt = contact.getText().toString();
                final String vectxt = Vec.getText().toString();
                final String addresstxt = Address.getText().toString();
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                //check if user fill the fields
                if(nametxt.isEmpty() || nictxt.isEmpty() || contacttxt.isEmpty() || vectxt.isEmpty() || addresstxt.isEmpty() || usernametxt.isEmpty() || passwordtxt.isEmpty()){
                    Toast.makeText(SIGNUP.this, "PLease fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if registered
                            if (snapshot.hasChild(nametxt)) {
                                Toast.makeText(SIGNUP.this, "User Registered", Toast.LENGTH_SHORT).show();
                            } else {
                                //send data to firebase realtime
                                databaseReference.child("user").child(nametxt).child("username").setValue(usernametxt);
                                databaseReference.child("user").child(nametxt).child("password").setValue(passwordtxt);
                                databaseReference.child("user").child(nametxt).child("Address").setValue(addresstxt);
                                databaseReference.child("user").child(nametxt).child("Vec").setValue(vectxt);
                                databaseReference.child("user").child(nametxt).child("contact").setValue(contacttxt);
                                databaseReference.child("user").child(nametxt).child("nic").setValue(nictxt);
                                databaseReference.child("user").child(nametxt).child("name").setValue(nametxt);



                                Toast.makeText(SIGNUP.this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

}