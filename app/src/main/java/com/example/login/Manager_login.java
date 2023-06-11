package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Manager_login extends AppCompatActivity {
    Button managerlogin;
    FirebaseAuth auth;

    EditText uname;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_emp_login);
        auth = FirebaseAuth.getInstance();
        uname = findViewById(R.id.loguname);
        pass = findViewById(R.id.logpass);
        managerlogin = findViewById(R.id.log);//get the id of the login button of xml
        managerlogin.setOnClickListener(new View.OnClickListener() { //the funtion starts after the click


            @Override
            public void onClick(View v) {

                loginUser();
            }
        });

    }
    private void loginUser(){
        String email = uname.getText().toString();
        String password = pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            uname.setError("Email Cannot be empty");
            uname.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            pass.setError("Password Cannot be empty");
            pass.requestFocus();
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Manager_login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Manager_login.this, Manager_home.class));

                        Intent intent = new Intent(Manager_login.this, Manager_home.class);//navigates from manager login to manager homepage
                        startActivity(intent);
                    }else{
                        Toast.makeText(Manager_login.this, "Error Login", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }

}