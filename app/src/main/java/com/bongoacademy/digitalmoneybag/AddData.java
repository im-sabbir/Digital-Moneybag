package com.bongoacademy.digitalmoneybag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddData extends AppCompatActivity {

    TextView title;
    EditText addAmount,addReason;
    Button insertButton;

    public static boolean EXPENSE = true;
    Databasehelper dbhelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        title = findViewById(R.id.title);
        addAmount = findViewById(R.id.addAmount);
        addReason = findViewById(R.id.addReason);
        insertButton = findViewById(R.id.insertButton);

        dbhelper = new Databasehelper(this);



        if (EXPENSE==true){
            title.setText("Add Expense");
        }else {
            title.setText("Add Income");
        }


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String edAmount = addAmount.getText().toString();
                String edReason = addReason.getText().toString();

                double amount = Double.parseDouble(edAmount);


                if (EXPENSE==true){
                    dbhelper.addexpense(amount, edReason);
                    title.setText("Expense Added");
                }else {
                    dbhelper.addincome(amount, edReason);
                    title.setText("Income Added");
                }





            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}