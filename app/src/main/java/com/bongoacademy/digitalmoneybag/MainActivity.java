package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView finalBalance,totalExpense, addExpense,allexpenselist,totalIncome,addIncome,allIncomelist;

    Databasehelper dbhelper;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finalBalance= findViewById(R.id.finalBalance);
        totalExpense= findViewById(R.id.totalExpense);
        addExpense= findViewById(R.id.addExpense);
        allexpenselist= findViewById(R.id.allexpenselist);
        totalIncome= findViewById(R.id.totalIncome);
        addIncome= findViewById(R.id.addIncome);
        allIncomelist= findViewById(R.id.allIncome);
        dbhelper = new Databasehelper(this);



        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddData.EXPENSE=true;
                startActivity(new Intent(MainActivity.this, AddData.class));
            }
        });


        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.EXPENSE=false;
                startActivity(new Intent(MainActivity.this, AddData.class));
            }
        });

        allexpenselist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDataList.EXPENSE=true;
                startActivity(new Intent(MainActivity.this, ShowDataList.class));
            }
        });

        allIncomelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDataList.EXPENSE=false;
                startActivity(new Intent(MainActivity.this, ShowDataList.class));
            }
        });



        UpdateUi();


    }


    //======================================================
    public void UpdateUi(){
        totalExpense.setText("BDT "+dbhelper.calculateAllExpense());
        totalIncome.setText("BDT "+dbhelper.calculateAllIncome());
        double balance = dbhelper.calculateAllIncome() - dbhelper.calculateAllExpense();
        finalBalance.setText("BDT "+balance);
    }


    //======================================================
    @Override
    protected void onPostResume() {
        super.onPostResume();
        UpdateUi();
    }
    //======================================================


}