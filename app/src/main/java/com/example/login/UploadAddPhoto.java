package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UploadAddPhoto extends AppCompatActivity {
    private static final int requestcamera_code= 12;

    ImageView imageView;
    Button takebtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_add_photo);

        imageView=findViewById(R.id.imgid);
        takebtn=findViewById(R.id.take_btn);

        takebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,requestcamera_code);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int ResultCode, Intent data){
        int resultCode = 0;
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==requestcamera_code){
            Bitmap imgbitmap=(Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(imgbitmap);
        }
    }
}