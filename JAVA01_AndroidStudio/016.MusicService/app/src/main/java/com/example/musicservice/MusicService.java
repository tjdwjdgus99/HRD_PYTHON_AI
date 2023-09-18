package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    private MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {  //  intent는 엑티비티에서 엑티비티로 전달
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp = MediaPlayer.create(this, R.raw.akmu);
        mp.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mp != null){
            mp.stop();
            mp.release();
        }
        super.onDestroy();
    }
}
