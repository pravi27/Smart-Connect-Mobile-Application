package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class update_stock extends AppCompatActivity {

    EditText octane92, octane95, ADiesel, SDiesel;
    Button updateStock;

    DatabaseReference smartconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock);

        // Initialize Firebase
        smartconnect = FirebaseDatabase.getInstance().getReference().child("Add_stock");

        // Initialize views
        octane92 = findViewById(R.id.octane92);
        octane95 = findViewById(R.id.octane95);
        ADiesel = findViewById(R.id.ADiesel);
        SDiesel = findViewById(R.id.SDiesel);
        updateStock = findViewById(R.id.UpdateStock);

        updateStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStock();
            }
        });
    }

    private void updateStock() {
        String newOctane92 = octane92.getText().toString();
        String newOctane95 = octane95.getText().toString();
        String newADiesel = ADiesel.getText().toString();
        String newSDiesel = SDiesel.getText().toString();

        // Update the values in the database
        smartconnect.child("octane92").setValue(newOctane92);
        smartconnect.child("octane95").setValue(newOctane95);
        smartconnect.child("ADiesel").setValue(newADiesel);
        smartconnect.child("SDiesel").setValue(newSDiesel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(update_stock.this, "Stock updated successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(update_stock.this, ManageStock.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(update_stock.this, "Failed to update stock", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
