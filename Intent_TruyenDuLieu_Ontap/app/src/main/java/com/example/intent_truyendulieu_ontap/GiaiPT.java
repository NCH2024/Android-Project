package com.example.intent_truyendulieu_ontap;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GiaiPT extends AppCompatActivity {
    //Khai báo các biến toàn cục
    EditText edtKetQua;
    Button btnBack;

    //Hàm onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giai_pt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Ánh xạ các thành phần giao diện
        edtKetQua = findViewById(R.id.edtKetQua);
        btnBack = findViewById(R.id.btnBack);

        //Tính kết quả
        Bundle bundle = getIntent().getBundleExtra("Delta");
        double soA = bundle.getDouble("soA");
        double soB = bundle.getDouble("soB");
        double soC = bundle.getDouble("soC");
        double delta = (soB * soB) - (4 * soA * soC);

        if (delta > 0){
            //có 2 nghiệm phân biệt
            double x1 = -soB + Math.sqrt(delta) / (2 * soA);
            double x2 = -soB - Math.sqrt(delta) / (2 * soA);

            edtKetQua.setText("x1: " + String.valueOf(x1) + "; x2: " + String.valueOf(x2));
        }
        else if (delta == 0) {
            //có nghiệm kép
            double x = -soB / (2 * soA);
            edtKetQua.setText("x: " + String.valueOf(x));
        }
        else {
            //không có nghiệm
            edtKetQua.setText("Phương trình vô nghiệm");
        }

        //Thiết lập sự kiện cho nút Back
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}