package com.example.sqlite_phan2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Khai báo các biến
    EditText edtTen, edtTuoi;
    Button btnInsert, btnSelect;
    DatabaseHelper myDb;
    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> dataList;

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
        //ánh xạ các biến
        edtTen = findViewById(R.id.edtTen);
        edtTuoi = findViewById(R.id.edtTuoi);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        lv = findViewById(R.id.lv);

        //Khoi tạo adapter và set adapter cho listView
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataList);
        lv.setAdapter(adapter);


        myDb = new DatabaseHelper(MainActivity.this);

        //Xử lý nút thêm dữ liệu
        btnInsert.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            String tuoi = edtTuoi.getText().toString();

            if (myDb.addData(ten, tuoi)) {
                Toast.makeText(MainActivity.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                edtTen.setText("");
                edtTuoi.setText("");
            }
            else {
                Toast.makeText(MainActivity.this, "Thêm dữ liệu thất bại", Toast.LENGTH_SHORT).show();
            }

        });

        btnSelect.setOnClickListener(v -> {
            adapter.clear();
            dataList = myDb.selectData();
            adapter.addAll(dataList);
            adapter.notifyDataSetChanged();

        });


    }

}