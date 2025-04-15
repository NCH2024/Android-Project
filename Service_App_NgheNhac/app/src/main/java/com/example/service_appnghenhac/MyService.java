package com.example.service_appnghenhac;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    MediaPlayer myMedia;

    @Override
    public IBinder onBind(Intent intent) {
        // Trả về null vì dịch vụ của em không phải là bound service
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            myMedia = MediaPlayer.create(MyService.this, R.raw.music);
            if (myMedia == null) {
                Log.e("MyService", "Không thể tạo MediaPlayer, kiểm tra lại file nhạc.");
            } else {
                Log.d("MyService", "MediaPlayer được tạo thành công.");
            }
            myMedia.setLooping(true);
        } catch (Exception e) {
            Log.e("MyService", "Lỗi khi tạo MediaPlayer: " + e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (myMedia != null) {
            if (!myMedia.isPlaying()) {
                myMedia.start();
                Log.d("MyService", "Nhạc bắt đầu phát.");
            } else {
                Log.d("MyService", "Nhạc đã đang phát.");
            }
        } else {
            Log.e("MyService", "MediaPlayer là null, không thể phát nhạc.");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myMedia != null) {
            myMedia.stop();
            myMedia.release(); // Giải phóng tài nguyên
            Log.d("MyService", "Nhạc dừng và tài nguyên được giải phóng.");
        }
    }
}
