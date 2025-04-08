package com.example.intent_truyendulieu_ontap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến toàn cục
    EditText soA, soB, soC;
    Button btnDelta, btnGiai, btnKT;

    // Hàm onCreate()
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

        // Ánh xạ các thành phần giao diện
        soA = findViewById(R.id.edtSoA);
        soB = findViewById(R.id.edtSoB);
        soC = findViewById(R.id.edtSoC);
        btnDelta = findViewById(R.id.btnDelta);
        btnGiai = findViewById(R.id.btnGiai);
        btnKT = findViewById(R.id.btnKT);

        // Thiết lập sự kiện cho các nút
        btnDelta.setOnClickListener(v -> {
            // Xử lý sự kiện khi nút DELTA được nhấn
            if (Integer.parseInt(soA.getText().toString())==0){
                Toast.makeText(this, "Phương trình nhập không phải phương trình bậc 2", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                Intent myIntent = new Intent(MainActivity.this, Delta.class);
                Bundle bundle = new Bundle();
                bundle.putInt("soA", Integer.parseInt(soA.getText().toString()));
                bundle.putInt("soB", Integer.parseInt(soB.getText().toString()));
                bundle.putInt("soC", Integer.parseInt(soC.getText().toString()));
                myIntent.putExtra("Delta", bundle);
                startActivity(myIntent);
            }
        });

        btnGiai.setOnClickListener(v -> {
            // Xử lý sự kiện khi nút GIẢI PT được nhấn
            if (Integer.parseInt(soA.getText().toString())==0){
                Toast.makeText(this, "Phương trình nhập không phải phương trình bậc 2", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                Intent myIntent = new Intent(MainActivity.this, GiaiPT.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("soA", Double.parseDouble(soA.getText().toString()));
                bundle.putDouble("soB", Double.parseDouble(soB.getText().toString()));
                bundle.putDouble("soC", Double.parseDouble(soC.getText().toString()));
                myIntent.putExtra("Delta", bundle);
                startActivity(myIntent);
            }
        });

        btnKT.setOnClickListener(v -> {
            // Xử lý sự kiện khi nút KIỂM TRA PHƯƠNG TRÌNH được nhấn
            if (Integer.parseInt(soA.getText().toString())==0){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("PHƯƠNG TRÌNH ĐANG NHẬP LÀ BẬC 1");
                builder.setPositiveButton("Thoát", (dialog, which) -> {
                    dialog.cancel();
                });
                builder.show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("PHƯƠNG TRÌNH ĐANG NHẬP LÀ BẬC 2");
                builder.setPositiveButton("Thoát", (dialog, which) -> {
                    dialog.cancel();
                });
                builder.show();
            }
    });
    }
}