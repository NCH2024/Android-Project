package com.example.intent_goi_dien_nhan_tin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NhanTin extends AppCompatActivity {
    //Khai báo các biến
    EditText edtSoDT;
    ImageButton imgbtnNhanTin;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhan_tin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Ánh xạ các biến
        edtSoDT = findViewById(R.id.edtSoDT);
        imgbtnNhanTin = findViewById(R.id.imgbtnNhanTin);
        btnBack = findViewById(R.id.btn_Back);

        //Gắn sự kiện click cho các nút
        btnBack.setOnClickListener(v -> {
            finish();
        });

        //Gắn sự kiện click cho nút gửi tin nhắn
        imgbtnNhanTin.setOnClickListener(v -> {
            String soDT = edtSoDT.getText().toString();
            if (soDT.isEmpty()) {
                edtSoDT.setError("Vui lòng nhập số điện thoại");
            } else {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + soDT));
                startActivity(intent);
            }
        });
    }
}