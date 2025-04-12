package com.example.intent_truyendulieu2;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCallerLauncher;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo biến
    EditText edtNhapData, edtKetQua;
    Button btnYeuCau;
    ActivityResultLauncher<Intent> activityResultLauncher;

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

        //Ánh xạ
        edtNhapData = findViewById(R.id.edtNhapData);
        edtKetQua = findViewById(R.id.edtKetQua);
        btnYeuCau = findViewById(R.id.btnYeuCau);

        activityResultLauncher = registerForActivityResult( 
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 2) {
                            Intent data = result.getData();
                            int kq = data.getIntExtra("kq", 0);
                            edtKetQua.setText("Kết quả gốc: " + kq);
                        }
                        if (result.getResultCode() == 3) {
                            Intent data = result.getData();
                            int kq = data.getIntExtra("kq", 0);
                            edtKetQua.setText("Kết quả bình phương: " + kq);
                        }
                    }
                }
        );

        

        btnYeuCau.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, XuLy.class);
            int a = Integer.parseInt(edtNhapData.getText().toString());
            intent.putExtra("a", a);
            activityResultLauncher.launch(intent);
        });
    }
}