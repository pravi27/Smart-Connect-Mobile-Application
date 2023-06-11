package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText UserName;
    EditText Name;
    EditText ContactNo;
    EditText NIC;
    EditText Address;
    EditText VehicleNo;
    Button SaveBtn;
    String UserNameT, NameT, ContactNoT, NICT, AddressT, VehicleNoT;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("user");

        UserName = findViewById(R.id.UserName);
        Name = findViewById(R.id.Name);
        ContactNo = findViewById(R.id.ContactNo);
        NIC = findViewById(R.id.NIC);
        Address = findViewById(R.id.Address);
        VehicleNo = findViewById(R.id.VehicleNo);
        SaveBtn = findViewById(R.id.Savebtn);

        showData();

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUserNameChanged() || isNameChanged() || isContactNoChanged() || isNICChanged() ||
                        isAddressChanged() || isVehicleNoChanged()) {
                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isUserNameChanged() {
        if (UserNameT != null && !UserNameT.equals(UserName.getText().toString())) {
            reference.child(UserNameT).child("UserName").setValue(UserName.getText().toString());
            UserNameT = UserName.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isNameChanged() {
        if (!NameT.equals(Name.getText().toString())) {
            reference.child(UserNameT).child("Name").setValue(Name.getText().toString());
            NameT = Name.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isContactNoChanged() {
        if (!ContactNoT.equals(ContactNo.getText().toString())) {
            reference.child(UserNameT).child("ContactNo").setValue(ContactNo.getText().toString());
            ContactNoT = ContactNo.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isNICChanged() {
        if (!NICT.equals(NIC.getText().toString())) {
            reference.child(UserNameT).child("NIC").setValue(NIC.getText().toString());
            NICT = NIC.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isAddressChanged() {
        if (!AddressT.equals(Address.getText().toString())) {
            reference.child(UserNameT).child("Address").setValue(Address.getText().toString());
            AddressT = Address.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isVehicleNoChanged() {
        if (!VehicleNoT.equals(VehicleNo.getText().toString())) {
            reference.child(UserNameT).child("VehicleNo").setValue(VehicleNo.getText().toString());
            VehicleNoT = VehicleNo.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData() {
        Intent intent = getIntent();

        UserNameT = intent.getStringExtra("UserName");
        NameT = intent.getStringExtra("Name");
        ContactNoT = intent.getStringExtra("ContactNo");
        NICT = intent.getStringExtra("NIC");
        AddressT = intent.getStringExtra("Address");
        VehicleNoT = intent.getStringExtra("VehicleNo");

        UserName.setText(UserNameT);
        Name.setText(NameT);
        ContactNo.setText(ContactNoT);
        NIC.setText(NICT);
        Address.setText(AddressT);
        VehicleNo.setText(VehicleNoT);
    }
}