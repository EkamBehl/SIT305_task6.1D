package com.example.truckapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SignUpPage extends AppCompatActivity {

    dbHelper db;
    EditText name,username,password,confirmPassword,phone;
    Button SignUp,clickPhoto;
    ImageView photo;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        name=findViewById(R.id.signUpName);
        username=findViewById(R.id.signUpUsername);
        password=findViewById(R.id.signUpPassword);
        confirmPassword=findViewById(R.id.signUpConfirmPassword);
        phone=findViewById(R.id.signUpPhone);
        SignUp=findViewById(R.id.createAccount);
        photo=findViewById(R.id.userPhoto);
        clickPhoto=findViewById(R.id.clickphotoButton);


        db =new dbHelper(this);

        
        clickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCameraPermission();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap=((BitmapDrawable)photo.getDrawable()).getBitmap();
                ByteArrayOutputStream str=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,0,str);
                byte[]byteArr =str.toByteArray();
                String imgc= byteArr.toString();
                String names=name.getText().toString();
                String pass=password.getText().toString();
                String confirPass=confirmPassword.getText().toString();
                String phn=phone.getText().toString();
                String userName=username.getText().toString();
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirPass) || TextUtils.isEmpty(phn)){
                    Toast.makeText(SignUpPage.this, "All fields are required!! ", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(confirPass)){
                        Boolean checkUser=db.checkUserName(userName);
                        if(!checkUser){
                            Boolean insertData=db.insertData(userName,pass,phn,names,imgc);
                            if(insertData.equals(true)){
                                Toast.makeText(SignUpPage.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                                Intent home=new Intent(SignUpPage.this,MainActivity.class);
                                startActivity(home);
                            }
                            else{
                                Toast.makeText(SignUpPage.this, "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUpPage.this, "User Already exists!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(SignUpPage.this, "passwords Don't match!!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }

    private void getCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},101);
        }else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==101){
            openCamera();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void openCamera() {
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        Bitmap img = (Bitmap)data.getExtras().get("data");
        photo.setImageBitmap(img);

    }
}