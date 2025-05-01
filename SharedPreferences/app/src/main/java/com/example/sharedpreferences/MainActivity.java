package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo biến
    EditText edtSoA, edtSoB, edtKetQua;
    Button btnCong, btnXoa;
    TextView txtLichSu;
    String lichSu = "";

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

        //ánh xạ các biến
        edtSoA = findViewById(R.id.edtsoA);
        edtSoB = findViewById(R.id.edtsoB);
        edtKetQua = findViewById(R.id.edtKetQua);
        btnCong = findViewById(R.id.btnCong);
        btnXoa = findViewById(R.id.btnXoa);
        txtLichSu = findViewById(R.id.txtLichSu);

        //Đọc lịch sử
        SharedPreferences sharedPreferences = getSharedPreferences("LichSu", MODE_PRIVATE);
        lichSu = sharedPreferences.getString("LichSu", "");
        txtLichSu.setText(lichSu);

        //Gắn sự kiện cho button Cộng
        btnCong.setOnClickListener(v -> {
            //Lấy giá trị
            int a = Integer.parseInt(edtSoA.getText().toString());
            int b = Integer.parseInt(edtSoB.getText().toString());
            //Tính toán
            int sum = a + b;
            //Hiển thị
            edtKetQua.setText(String.valueOf(sum));
            //Lưu lịch sử
            lichSu += a + " + " + b + " = " + sum + " ";
            txtLichSu.setText(lichSu);
            lichSu += "\n";
        });

        //Gắn sự kiện cho button Xóa
        btnXoa.setOnClickListener(v -> {
            edtSoA.setText("");
            edtSoB.setText("");
            edtKetQua.setText("");
            lichSu = "";
            txtLichSu.setText(lichSu);
    });
}

    @Override
    protected void onPause() {
        super.onPause();
        //Bước 1: Tao SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("LichSu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Bước 2: Lưu lịch sử
        editor.putString("LichSu", lichSu);
        //Bước 3: Commit
        editor.apply();
        //editor.commit();
    }
}