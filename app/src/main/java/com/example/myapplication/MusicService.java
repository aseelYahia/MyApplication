package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.musicrington);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);

    }
    @Override
    public int onStartCommand(Intent intent,int flags, int startId)
    {
        mediaPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mediaPlayer.stop();
    }
}