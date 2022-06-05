package com.example.truckapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class myOrders extends AppCompatActivity {
    String sender,receiver,time,date,location,length,width,height,weight,goodType,vehicleType;
    TextView senderTEXT,weightTEXT,goodTypeTEXT,widthTEXT,heightTEXT,lengthTEXT,timeTEXT,recieverTEXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        senderTEXT=findViewById(R.id.senderText);
        timeTEXT=findViewById(R.id.pickTimeText);
        recieverTEXT=findViewById(R.id.recieverText);
        weightTEXT=findViewById(R.id.weightsText);
        heightTEXT=findViewById(R.id.heightTEXT);
        lengthTEXT=findViewById(R.id.lengthTEXT);
        goodTypeTEXT=findViewById(R.id.goodTypeTEXT);
        widthTEXT=findViewById(R.id.widthTEXT);
        Intent getIntent=getIntent();
        sender=getIntent.getStringExtra("sender");
        receiver=getIntent.getStringExtra("receiver");
        time=getIntent.getStringExtra("time");
        date=getIntent.getStringExtra("date");
        location=getIntent.getStringExtra("location");
        length=getIntent.getStringExtra("length");
        width=getIntent.getStringExtra("width");
        height=getIntent.getStringExtra("height");
        weight=getIntent.getStringExtra("weight");
        goodType=getIntent.getStringExtra("goodType");
        vehicleType=getIntent.getStringExtra("vehicleType");

        senderTEXT.setText(sender);
        timeTEXT.setText(time);
        recieverTEXT.setText(receiver);
        weightTEXT.setText("weight : "+weight);
        heightTEXT.setText("height: "+height);
        lengthTEXT.setText("length: "+length);
        goodTypeTEXT.setText(goodType);
        widthTEXT.setText("width: " +width);
    }
}