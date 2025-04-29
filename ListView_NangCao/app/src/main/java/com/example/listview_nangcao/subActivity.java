package com.example.listview_nangcao;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class subActivity extends AppCompatActivity {
    //khai báo biến
    TextView txtSubPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Tham chiếu id
        txtSubPhone = findViewById(R.id.txtSubPhone);
        //Lấy dữ liệu từ intent
        String name = getIntent().getStringExtra("name");
        //Hiển thị dữ liệu lên TextView
        txtSubPhone.setText(name);

        //xử lý click back
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

    }
}