package com.example.intent_goi_dien_nhan_tin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo các biến
    Button btnGoi, btnNhanTin;

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

        //Ánh xạ các biến
        btnGoi = findViewById(R.id.btnGoi);
        btnNhanTin = findViewById(R.id.btnNhantin);

        //Gắn sự kiện click cho các nút
        btnGoi.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GoiDien.class));
        });
        btnNhanTin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NhanTin.class));
        });

    }
}