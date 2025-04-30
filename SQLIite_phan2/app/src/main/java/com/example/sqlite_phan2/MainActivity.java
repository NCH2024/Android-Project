package com.example.sqlite_phan2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo các biến
    EditText edtTen, edtTuoi;
    Button btnInsert;
    DatabaseHelper myDb;
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
        edtTen = findViewById(R.id.edtTen);
        edtTuoi = findViewById(R.id.edtTuoi);
        btnInsert = findViewById(R.id.btnInsert);

        myDb = new DatabaseHelper(this);

        //Xử lý nút thêm dữ liệu
        btnInsert.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            String tuoi = edtTuoi.getText().toString();
            String msg ="";
            try {
                myDb.deleteData(ten);
                msg = "Thêm dữ liệu thành công";
            }catch (Exception e){
                msg = "Thêm dữ liệu thất bại";
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });
    }

}