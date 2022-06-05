package com.example.truckapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    public static final String Table_Name="login";
    public dbHelper(Context context) {
        super(context, Table_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(username TEXT primary key,name TEXT,password TEXT,phone TEXT,image TEXT )");
        db.execSQL("CREATE TABLE orders(ID integer primary key autoincrement ,username TEXT,receiver TEXT,date TEXT,time TEXT,location TEXT,goodType TEXT," +
                "weight TEXT,width TEXT,length TEXT,height TEXT,vehicleType TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if exists user");
        db.execSQL("DROP TABLE if exists orders");

    }
    public Boolean addOrder(String userName,String receiver ,String date,String time,String location,String goodType,String weight,String height,String length,String width,String vehicleType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put("username", userName);
        vals.put("date", date);
        vals.put("time",time);
        vals.put("location",location);
        vals.put("goodType",goodType);
        vals.put("weight",weight);
        vals.put("height",height);
        vals.put("width",width);
        vals.put("length",length);
        vals.put("receiver",receiver);
        vals.put("vehicleType ",vehicleType);



        long result=db.insert("orders",null,vals);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor fetchOrders(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM orders",null);
        return data;
    }
    public Boolean insertData(String userName ,String password,String phone,String name,String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put("username", userName);
        vals.put("password", password);
        vals.put("name",name);
        vals.put("phone",phone);
        vals.put("image",image);


        long result=db.insert("user",null,vals);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkUserName(String userName){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from user where username=?",new String[] {userName});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkUserPassword(String userName,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from user where username=? and password=?",new String[] {userName,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

}
