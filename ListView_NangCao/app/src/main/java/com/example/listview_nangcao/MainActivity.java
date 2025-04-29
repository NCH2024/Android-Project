package com.example.listview_nangcao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String[] name = {"Iphone 16 Pro Max", "SamSung s25 Ultra", "Realme Note 12", "Oppo F11 Pro"};
    int[] image = {R.drawable.iphone, R.drawable.samsung, R.drawable.realme, R.drawable.oppo};
    ArrayList<Phone> mylist;
    MyArrayAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Tham chiếu id
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            mylist.add(new Phone(image[i], name[i]));
        }
        myadapter = new MyArrayAdapter(this, R.layout.layitem, mylist);
        lv.setAdapter(myadapter);

        //xử lý click
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, subActivity.class);
            intent.putExtra("name", mylist.get(position).getName());
            startActivity(intent);
        });
        }
    }
