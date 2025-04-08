package com.example.contraint_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //khai báo các biến giao diện tại đây
    EditText soA, soB, kq;
    Button btn_kq;

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

        //Ánh xạ các id đã truyền trên giao diện qua các biến được khai báo
        soA = findViewById(R.id.soA);
        soB = findViewById(R.id.soB);
        kq = findViewById(R.id.kq);
        btn_kq = findViewById(R.id.btn_kq);

        //Xử lý tương tác nút cộng
        btn_kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(soA.getText().toString());
                int b = Integer.parseInt(soB.getText().toString());

                int c = a + b ;

                kq.setText(c + ""); //Hiển thị kết quả

            }
        });
    }
}