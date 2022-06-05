package com.example.truckapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class new_order extends AppCompatActivity {

    String sender,receiver,date,time,location;
    EditText dateEdit,timeEdit,locationEdit,receiverEdit;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        dateEdit=findViewById(R.id.dateEdit);
        timeEdit=findViewById(R.id.timeEditText);
        locationEdit=findViewById(R.id.pickLocationEdit);
        receiverEdit=findViewById(R.id.receiverNameEdit);
        next=findViewById(R.id.nextButon);


        sender=getIntent().getStringExtra("username");

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                DatePickerDialog dialog=new DatePickerDialog(new_order.this,dateSetListener,year,month,day);
            }
        });
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                date=month+"/"+dayOfMonth+"/"+year;

            }
        };
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                receiver=receiverEdit.getText().toString();
                time=timeEdit.getText().toString();
                location=locationEdit.getText().toString();
                Intent intent=new Intent(getBaseContext(),goodsType.class);
                intent.putExtra("sender",sender);
                intent.putExtra("receiver",receiver);
                intent.putExtra("time",time);
                intent.putExtra("date",date);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
    }
}