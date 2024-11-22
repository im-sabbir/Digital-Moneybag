package com.bongoacademy.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(Context context) {
        super(context, "digital_moneybag", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time TEXT)" );
        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists income");
        db.execSQL("drop table if exists expense");
    }




    //=================================================
    public void addexpense(double amount, String reason){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues convel = new ContentValues();
        convel.put("amount", amount);
        convel.put("reason", reason);
        //convel.put("time", System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss \n dd-MM-yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        convel.put("time", currentDateandTime);


        db.insert("expense", null, convel);
    }

    //==================================================

    //=================================================
    public void addincome(double amount, String reason){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues convel = new ContentValues();
        convel.put("amount", amount);
        convel.put("reason", reason);
       // convel.put("time", System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss \n dd-MM-yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        convel.put("time", currentDateandTime);

        db.insert("income", null, convel);
    }

    //==================================================
    public double calculateAllExpense(){

        double totalExpense = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense", null);

        if (cursor!=null && cursor.getCount()>0){

            while (cursor.moveToNext()){

                double expense = cursor.getDouble(1);
                totalExpense =totalExpense+expense;
            }
        }

        return totalExpense;
    }
    //==================================================


    //==================================================
    public double calculateAllIncome(){

        double totalIncome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income", null);

        if (cursor!=null && cursor.getCount()>0){

            while (cursor.moveToNext()){

                double income = cursor.getDouble(1);
                totalIncome =totalIncome+income;
            }
        }

        return totalIncome;
    }
    //==================================================


    public Cursor getallExpenseData(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense",null);

        return cursor;
    }


    public Cursor getallIncomeData(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income",null);

        return cursor;
    }


    public void deleteExpenseList(String id){

        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL(" delete from expense where id like "+id);
    }

    public void deleteIncomeList(String id){

        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL(" delete from income where id like "+id);
    }
    
}
