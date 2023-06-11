package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://smart-connect-b162d-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cust);
        final EditText username = findViewById(R.id.loguname);
        final EditText password = findViewById(R.id.logpass);
        final TextView login = findViewById(R.id.log);
        final TextView signup = findViewById(R.id.signup);
        final TextView forgot = findViewById(R.id.forgot_password);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open signup
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open signup
                Intent intent = new Intent(Login.this, SIGNUP.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String unametxt = username.getText().toString();
                final String passtxt = password.getText().toString();

                if (unametxt.isEmpty() || passtxt.isEmpty()) {
                    Toast.makeText(Login.this, "Enter details", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(unametxt)) {
                                final String getPassword = snapshot.child(unametxt).child("password").getValue(String.class);

                                if (getPassword.equals(passtxt)) {
                                    Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, cus.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, cus.class));
                                    finish();
                                }
                            } else {
                                Toast.makeText(Login.this, "Wrong Username", Toast.LENGTH_SHORT).show();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
        });


    }
}





