package com.example.table_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Khai báo các biến giao diện
    EditText edtNamDuong;
    Button btnConvert;
    TextView tvAmLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Tham chiếu các biến trên giao diện
        edtNamDuong = findViewById(R.id.edtNam);
        btnConvert = findViewById(R.id.btnConvert);
        tvAmLich = findViewById(R.id.txAmLich);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->   {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] canArray = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
                String[] chiArray = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"};

                // Lấy năm dương lịch từ EditText
                int namDuong = Integer.parseInt(edtNamDuong.getText().toString());

                // Tính Can và Chi
                String can = canArray[namDuong % 10];
                String chi = chiArray[namDuong % 12];

                // Hiển thị kết quả
                tvAmLich.setText(can + " " + chi);
            }
        });
    }
}