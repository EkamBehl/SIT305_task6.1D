package com.example.truckapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText passWord;

    Button login;
    Button signUp;

    dbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.loginUserName);
        passWord=findViewById(R.id.loginPassword);

        login=findViewById(R.id.LoginButton);
        signUp=findViewById(R.id.SignUpButton);

        db=new dbHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSignUp= new Intent(MainActivity.this,SignUpPage.class);
                startActivity(openSignUp);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=userName.getText().toString();
                String password=passWord.getText().toString();

                Boolean authenticateUser=db.checkUserPassword(user,password);
                if(authenticateUser){
                    Intent intents=new Intent(MainActivity.this,homePage.class);
                    intents.putExtra("username",user);
                    startActivity(intents);
                }else{
                    Toast.makeText(MainActivity.this, "Invalid User Name or password ", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}