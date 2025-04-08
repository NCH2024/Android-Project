package com.example.intent_truyendulieu_ontap;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Delta extends AppCompatActivity {
    //Khai báo các biến toàn cục
    EditText edtDelta;
    Button btnBackDelta;

    //Hàm onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Ánh xạ các thành phần giao diện
        edtDelta = findViewById(R.id.edtDelta);
        btnBackDelta = findViewById(R.id.btnBackDelta);

        //Tính Delta
        Bundle bundle = getIntent().getBundleExtra("Delta");
        double soA = bundle.getDouble("soA");
        double soB = bundle.getDouble("soB");
        double soC = bundle.getDouble("soC");
        double delta = (soB * soB) - (4 * soA * soC);
        edtDelta.setText(String.valueOf(delta));

        //Thiết lập sự kiện cho nút Back
        btnBackDelta.setOnClickListener(v -> {
            //Xử lý sự kiện khi nút Back được nhấn
            edtDelta.setText("");
            finish();
        });
    }
}