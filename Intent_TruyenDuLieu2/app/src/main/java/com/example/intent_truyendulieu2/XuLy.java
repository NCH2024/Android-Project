package com.example.intent_truyendulieu2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XuLy extends AppCompatActivity {
    //Khai báo biến
    EditText edtDataNhan;
    Button btnGuiGoc, btnGuiBPhuong;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xu_ly);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Ánh xạ
        edtDataNhan = findViewById(R.id.edtDataNhan);
        btnGuiGoc = findViewById(R.id.btnGuiGoc);
        btnGuiBPhuong = findViewById(R.id.btnGuiBPhuong);

        //Nhận dữ liệu
        intent = getIntent();
        int a = intent.getIntExtra("a", 0);
        edtDataNhan.setText(String.valueOf(a));

        //Sự kiện
        btnGuiGoc.setOnClickListener(v -> {
            intent.putExtra("kq", a);
            setResult(2, intent);
            finish();
        });

        btnGuiBPhuong.setOnClickListener(v -> {
           intent.putExtra("kq", a*a);
           setResult(3, intent);
           finish();
        });
    }
}