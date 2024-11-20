package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView finalBalance,totalExpense, addExpense,allexpense,totalIncome,addIncome,allIncome;

    Databasehelper dbhelper;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finalBalance= findViewById(R.id.finalBalance);
        totalExpense= findViewById(R.id.totalExpense);
        addExpense= findViewById(R.id.addExpense);
        allexpense= findViewById(R.id.allexpense);
        totalIncome= findViewById(R.id.totalIncome);
        addIncome= findViewById(R.id.addIncome);
        allIncome= findViewById(R.id.allIncome);
        dbhelper = new Databasehelper(this);



        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddData.class));
            }
        });



        UpdateUi();


    }


    //======================================================
    public void UpdateUi(){
        totalExpense.setText("BDT "+dbhelper.calculateAllExpense());
    }


    //======================================================
    @Override
    protected void onPostResume() {
        super.onPostResume();
        UpdateUi();
    }
    //======================================================


}