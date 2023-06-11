package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class Myprofile_Cust extends AppCompatActivity {

    TextView UserName, Name, ContactNo, NIC, Address, VehicleNo;

    Button editProfile;
    private ImageView ProfilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_cust);
        ProfilePic = findViewById(R.id.PP);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choossePicture();

            }
        });

        UserName = findViewById(R.id.UserName);
        Name = findViewById(R.id.Name);
        ContactNo = findViewById(R.id.ContactNo);
        NIC = findViewById(R.id.NIC);
        Address = findViewById(R.id.Address);
        VehicleNo = findViewById(R.id.VehicleNo);
        editProfile = findViewById(R.id.editProfile);

        showUserData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });
    }
    //Profile pic choose
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            ProfilePic.setImageURI(imageUri);
            UploadPicture();
        }
    }

    private void UploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image.....");
        pd.show();

        final String randomkey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("ProfilePics/" +randomkey);
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();

                        //uploaded
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        // Handle unsuccessful upload
                        Toast.makeText(getApplicationContext(),"Failed to Upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Precentage: " + (int) progressPercent + "%" );

                    }
                });



}

    private void choossePicture() {

        Intent intent = new Intent();
        intent.setType("ProfilePics/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }


    public void showUserData() {
        Intent intent = getIntent();

        String userName = intent.getStringExtra("UserName");
        String name = intent.getStringExtra("Name");
        String contactNo = intent.getStringExtra("ContactNo");
        String nic = intent.getStringExtra("NIC");
        String address = intent.getStringExtra("Address");
        String vehicleNo = intent.getStringExtra("VehicleNo");

        UserName.setText(userName);
        Name.setText(name);
        ContactNo.setText(contactNo);
        NIC.setText(nic);
        Address.setText(address);
        VehicleNo.setText(vehicleNo);
    }


    public void passUserData() {
        String userUsername = getIntent().getStringExtra("UserName");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String userNameFromDB = dataSnapshot.child("UserName").getValue(String.class);
                        String nameFromDB = dataSnapshot.child("Name").getValue(String.class);
                        String contactNoFromDB = dataSnapshot.child("ContactNo").getValue(String.class);
                        String nicFromDB = dataSnapshot.child("NIC").getValue(String.class);
                        String addressFromDB = dataSnapshot.child("Address").getValue(String.class);
                        String vehicleNoFromDB = dataSnapshot.child("VehicleNo").getValue(String.class);

                        Intent intent = new Intent(Myprofile_Cust.this, EditProfileActivity.class);
                        intent.putExtra("UserName", userNameFromDB);
                        intent.putExtra("Name", nameFromDB);
                        intent.putExtra("ContactNo", contactNoFromDB);
                        intent.putExtra("NIC", nicFromDB);
                        intent.putExtra("Address", addressFromDB);
                        intent.putExtra("VehicleNo", vehicleNoFromDB);

                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editProfile=findViewById(R.id.editProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Myprofile_Cust.this, EditProfileActivity.class);
                startActivity(intent);


            }
        });
    }
}