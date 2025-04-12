package com.example.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //khai báo các biến
    EditText edtten, edtemail, edtsdt;
    Button dk;
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


        //Tham chieu caca bien giao dien
        edtten = findViewById(R.id.edtten);
        edtemail = findViewById(R.id.edtemail);
        edtsdt = findViewById(R.id.edtsdt);
        dk = findViewById(R.id.button);

        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ EditText
                String ten = edtten.getText().toString();
                String email = edtemail.getText().toString();
                String sdt = edtsdt.getText().toString();
                // Hiển thị thông báo
                AlertDialog.Builder tbdangky = new AlertDialog.Builder(MainActivity.this);
                tbdangky.setTitle("Đã đang ký thành viên");
                tbdangky.setMessage("Tên: " + ten + "\nEmail: " + email + "\nSDT: " + sdt);
                tbdangky.setIcon(R.drawable.ic_action_name);
                tbdangky.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                tbdangky.create().show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        //tao dialog
        AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
        mydialog.setTitle("Thông báo");
        mydialog.setMessage("Bạn có chắc chắn thoát ứng dụng!");
        mydialog.setIcon(R.drawable.ic_action_name);
        mydialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        mydialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        mydialog.create().show();
    }
}