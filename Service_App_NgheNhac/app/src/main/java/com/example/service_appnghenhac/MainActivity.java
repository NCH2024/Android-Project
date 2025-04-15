package com.example.service_appnghenhac;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Khai báo các biến
    ImageButton btnPlay, btnStop;
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
        //Tham chiếu các biến
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnStop.setEnabled(false);
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music); Dòng này dùng để thử âm thanh trực tiếp khi khởi chạy
//        mediaPlayer.start();


        btnPlay.setOnClickListener(v -> {
            //Intent khởi động service
            Intent intent1 = new Intent(MainActivity.this, MyService.class);
            //Thay đổi hình ảnh khi click
            btnPlay.setImageResource(R.drawable.pause);
            startService(intent1);
            btnStop.setEnabled(true);
        });

        btnStop.setOnClickListener(v -> {
            //Intent dừng service
            Intent intent2 = new Intent(MainActivity.this, MyService.class);
            btnPlay.setImageResource(R.drawable.play);
            stopService(intent2);
            btnStop.setEnabled(false);
        });
    }
}