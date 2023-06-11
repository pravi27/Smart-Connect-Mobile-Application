package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class emp_scan_qr extends AppCompatActivity {
    public static TextView resultTextView;
    Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_scan_qr);

        resultTextView = (TextView) findViewById(R.id.textResult);
        scan_btn = (Button) findViewById(R.id.scannow);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),scanned_code_activity.class));
            }
        });


    }
}