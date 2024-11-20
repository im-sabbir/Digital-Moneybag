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





        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String edAmount = addAmount.getText().toString();
                String edReason = addReason.getText().toString();

                double amount = Double.parseDouble(edAmount);

                dbhelper.addexpense(amount, edReason);

                Toast.makeText(AddData.this, "Data inserted", Toast.LENGTH_SHORT).show();
                title.setText("Data Inserted!!");

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}