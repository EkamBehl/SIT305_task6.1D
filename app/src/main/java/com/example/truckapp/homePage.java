package com.example.truckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class homePage extends AppCompatActivity {

    String userName,receiver ,date,time,location,goodType,weight,height,length,width,vehicleType;
    dbHelper db;
    Cursor data;
    Button newOrder;
    ListView listView;
    ArrayList<String> list;
    String userNAME;
    ArrayList<String> temp;
    ArrayList<ArrayList<String>> cursorList;
    ArrayList<ArrayList<String>> singleUserCursorList;
    Boolean isMyorder=false;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menua,menu);
        return true;
    }
    public void getAllOrders(){
        list=new ArrayList<>();
        db=new dbHelper(this);
        data=db.fetchOrders();
        cursorList=new ArrayList<ArrayList<String>>();
        while(data.moveToNext()){
            userName=data.getString(1);
            receiver=data.getString(2);
            date=data.getString(3);
            time=data.getString(4);
            location=data.getString(5);
            goodType=data.getString(6);
            weight=data.getString(7);
            height=data.getString(8);
            length=data.getString(9);
            width=data.getString(10);
            vehicleType=data.getString(11);
            temp=new ArrayList<>(Arrays.asList(userName,receiver,date,time,location,goodType,weight,height,length,width,vehicleType));
            list.add("GoodType:"+ goodType+" Truck Type:"+vehicleType);
            cursorList.add(temp);
        }
        ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


    }
    public void getUserOrders(){

        list=new ArrayList<>();
        db=new dbHelper(this);
        data=db.fetchOrders();
        cursorList=new ArrayList<ArrayList<String>>();

        while(data.moveToNext()){
            userName=data.getString(1);
            if(userName.equals(userNAME)){

                receiver=data.getString(2);
                date=data.getString(3);
                time=data.getString(4);
                location=data.getString(5);
                goodType=data.getString(6);
                weight=data.getString(7);
                height=data.getString(8);
                length=data.getString(9);
                width=data.getString(10);
                vehicleType=data.getString(11);
                list.add("GoodType:"+ goodType+" Truck Type:"+vehicleType);
                temp=new ArrayList<>(Arrays.asList(userNAME,receiver,date,time,location,goodType,weight,height,length,width,vehicleType));
                cursorList.add(temp);
            }



        }
        ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.home){
            getAllOrders();
            isMyorder=false;
        }
        if(itemId==R.id.account){
            Intent j=new Intent(getBaseContext(),homePage.class);
            startActivity(j);
        }
        if(itemId==R.id.myOrders){
            getUserOrders();
            isMyorder=true;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db=new dbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        listView=findViewById(R.id.listView);
        userNAME=getIntent().getStringExtra("username");
        getAllOrders();
        String username=getIntent().getStringExtra("username");
        newOrder=findViewById(R.id.newOrder);
        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),new_order.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getBaseContext(),myOrders.class);

                intent.putExtra("sender",cursorList.get(position).get(1));
                intent.putExtra("receiver",cursorList.get(position).get(2));
                intent.putExtra("date",cursorList.get(position).get(3));
                intent.putExtra("time",cursorList.get(position).get(4));
                intent.putExtra("location",cursorList.get(position).get(5));
                intent.putExtra("goodType",cursorList.get(position).get(6));
                intent.putExtra("weight",cursorList.get(position).get(7));
                intent.putExtra("height",cursorList.get(position).get(8));
                intent.putExtra("length",cursorList.get(position).get(9));
                intent.putExtra("width",cursorList.get(position).get(10));
                //intent.putExtra("vehicleType",cursorList.get(position).get(11));

                startActivity(intent);

            }
        });

    }
}