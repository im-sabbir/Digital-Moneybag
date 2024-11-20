package com.bongoacademy.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(Context context) {
        super(context, "digital_moneybag", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)" );
        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");
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
        convel.put("time", System.currentTimeMillis());

        db.insert("expense", null, convel);
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




    
}
