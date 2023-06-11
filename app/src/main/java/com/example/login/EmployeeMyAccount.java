package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeMyAccount extends AppCompatActivity {
    Button edit_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_my_account);
        edit_emp=findViewById(R.id.emp_profile_edit);
        edit_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeMyAccount.this, EditEmployeeProfile.class);
                startActivity(intent);


            }
        });
    }
}