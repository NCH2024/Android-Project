package com.example.listview_co_ban;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo dữ liệu mẫu
    String data[]   = {"SAMSUNG", "IPHONE", "OPPO", "VSMART", "NOKIA", "XIAOMI"};
    ArrayAdapter<String> adapter;
    ListView lvDS;
    TextView txtKetQua;

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
        //ánh xạ id
        lvDS = findViewById(R.id.lvDS);
        txtKetQua = findViewById(R.id.txtKetQua);

        //tạo adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        //gan adapter cho listview
        lvDS.setAdapter(adapter);

        //xử lý cho listview
        lvDS.setOnItemClickListener((parent, view, position, id) -> {
            txtKetQua.setText("Vị trí: " + position + "\n" + "Giá trị: " + data[position]);
        });
    }
}