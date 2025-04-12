package com.example.intent_goi_dien_nhan_tin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GoiDien extends AppCompatActivity {
    //Khai báo các biến
    EditText edtSoDT;
    ImageButton imgbtnGoi;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goi_dien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Ánh xạ các biến
        edtSoDT = findViewById(R.id.edtSoDT);
        imgbtnGoi = findViewById(R.id.imgbtnNhanTin);
        btnBack = findViewById(R.id.btnBack);

        //Gắn sự kiện click cho các nút
        btnBack.setOnClickListener(v -> {
            finish();
        });

        imgbtnGoi.setOnClickListener(v -> {
            String soDT = edtSoDT.getText().toString();
            if (soDT.isEmpty()) {
                edtSoDT.setError("Vui lòng nhập số điện thoại");
            } else {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + soDT));
                startActivity(intent);
            }
        });
    }
}
