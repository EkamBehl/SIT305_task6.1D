package com.example.truckapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class goodsType extends AppCompatActivity {

    String lengths,weights,heights,widths,vehicleType,goodType;

    String sender,receiver,time,date,location;


    dbHelper db;

    EditText weight,length,width,height;
    Button createOrder;
    RadioGroup vehicleTypeGroup,goodsTypeGroup;
    RadioButton furnitureRadio,dryGoodRadio,foodRadio,buildingRadio,truckRadio,vanRadio,refrigeratedRadio,miniTruckRadio;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new dbHelper(this);
        Intent getIntent=getIntent();
        sender=getIntent.getStringExtra("sender");
        receiver=getIntent.getStringExtra("receiver");
        time=getIntent.getStringExtra("time");
        date=getIntent.getStringExtra("date");
        location=getIntent.getStringExtra("location");



        setContentView(R.layout.activity_goods_type);
        vehicleTypeGroup=findViewById(R.id.vehicle_type_group);
        goodsTypeGroup=findViewById(R.id.goodTypeRadioGroup);

        furnitureRadio=findViewById(R.id.furnitureRadio);
        dryGoodRadio=findViewById(R.id.dryGoodsRadio);
        foodRadio=findViewById(R.id.foodRadio);
        buildingRadio=findViewById(R.id.buildingMaterialRadio);
        truckRadio=findViewById(R.id.truckRadio);
        vanRadio=findViewById(R.id.vanRadio);
        refrigeratedRadio=findViewById(R.id.refrigeratedTruckRadio);
        miniTruckRadio=findViewById(R.id.miniTruckRadio);
        createOrder=findViewById(R.id.createOrder);
        weight=findViewById(R.id.weightsEdit);
        length=findViewById(R.id.lengthEdit);
        height=findViewById(R.id.heightEdit);
        width=findViewById(R.id.widthEdit);

        vehicleTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==truckRadio.getId()){
                    vehicleType=truckRadio.getText().toString();
                }
                if(checkedId==vanRadio.getId()){
                    vehicleType=vanRadio.getText().toString();
                }
                if(checkedId==refrigeratedRadio.getId()){
                    vehicleType=refrigeratedRadio.getText().toString();
                }
                if(checkedId==miniTruckRadio.getId()){
                    vehicleType=miniTruckRadio.getText().toString();
                }
            }
        });
        goodsTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==dryGoodRadio.getId()){
                    goodType=dryGoodRadio.getText().toString();
                }
                if(checkedId==furnitureRadio.getId()){
                    goodType=furnitureRadio.getText().toString();
                }
                if(checkedId==foodRadio.getId()){
                    goodType=foodRadio.getText().toString();
                }
                if(checkedId==buildingRadio.getId()){
                    goodType=buildingRadio.getText().toString();
                }
            }
        });
        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lengths=length.getText().toString();
                widths=width.getText().toString();
                heights=height.getText().toString();
                weights=weight.getText().toString();
                boolean orderAdded=db.addOrder(sender,receiver,date,time,location,goodType,weights,heights,lengths,widths,vehicleType);
                if(orderAdded){
                    Toast.makeText(goodsType.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(goodsType.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}