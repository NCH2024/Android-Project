package com.example.intent_chupanh_quayvideo;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import android.Manifest;
import android.app.ComponentCaller;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo các biến
    private ImageView imageView;
    private ImageButton imageButton;
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
        //Ánh xạ các biến
        imageView = findViewById(R.id.imageView);
        imageButton = findViewById(R.id.imageButton);

        //Xử lý sự kiện khi imageButton được click
        imageButton.setOnClickListener(v -> {
            //Khai báo intent gọi đến action_image_capture dùng chụp ảnh
            Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
            //Yêu cầu quyền truy cập camera
            if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},0);
                return;
            }
            //Nếu đã đồng ý thì khởi động intent và nhận kết quả trả về
            startActivityForResult(intent,99);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);
        if(requestCode==99 && resultCode==RESULT_OK){
            //Lấy bitmap từ data trả về
            Bitmap bitmap = data.getParcelableExtra("data");
            //Thiết lập bitmap vào imageView
            imageView.setImageBitmap(bitmap);
        }
    }
}