package com.example.intent_truyendulieu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {
    EditText edtResult;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtResult = findViewById(R.id.edtResult);
        btnBack = findViewById(R.id.btnBack);
        //Xử lý btnBack
        btnBack.setOnClickListener(v -> {
            finish();
        });
        //Lấy dữ liệu từ Bundle
        Bundle bundle = getIntent().getBundleExtra("soAvaB");
        double a = bundle.getDouble("a");
        double b = bundle.getDouble("b");
        if (a == 0) {
            if (b == 0) {
                edtResult.setText("Vô số nghiệm");
            } else {
                edtResult.setText("Vô nghiệm");
            }
        }
        else {
            double x = -b / a;
            edtResult.setText("Nghiệm x = " + x);
        }
    }
}