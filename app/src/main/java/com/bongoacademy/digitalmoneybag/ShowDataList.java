package com.bongoacademy.digitalmoneybag;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowDataList extends AppCompatActivity {

    TextView title;
    ListView listview;
    Databasehelper databasehelper;

    ArrayList<HashMap<String, String>> arrayList;
    HashMap<String,String> hashMap;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_list);

        title = findViewById(R.id.title);
        listview = findViewById(R.id.listview);
        databasehelper = new Databasehelper(this);

        Cursor cursor = databasehelper.getallExpenseData();

        if (cursor!=null && cursor.getCount()>0){

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()){
                int id= cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                String time = cursor.getString(3);

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("amount", ""+amount);
                hashMap.put("reason", ""+reason);
                hashMap.put("time", ""+time);
                arrayList.add(hashMap);
            }


            listview.setAdapter(new MyAdapter());


        }else {
            title.setText("No data found");
        }





    }


    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View myview = inflater.inflate(R.layout.list_item, parent,false);

            TextView tvreason = myview.findViewById(R.id.reason);
            TextView tvamount = myview.findViewById(R.id.amount);
            TextView tvdate = myview.findViewById(R.id.date);
            TextView tvdelete = myview.findViewById(R.id.delete);

            hashMap = arrayList.get(position);

            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String reason = hashMap.get("reason");
            String time = hashMap.get("time");

            tvamount.setText(amount);
            tvreason.setText(reason);
            tvdate.append("\n"+time);

            return myview;

        }
    }
}