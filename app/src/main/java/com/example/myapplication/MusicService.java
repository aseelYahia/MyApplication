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
        int[] r ={
                R.raw.all_too_well_record,
                R.raw.blank_space_record,
                R.raw.cardigan_record,
                R.raw.champagne_problems_record,
                R.raw.clean_record,
                R.raw.cornilia_street_record,
                R.raw.enchanted_record,
                R.raw.hey_stephen_record,
                R.raw.new_years_record,
                R.raw.the_last_time_record,
                R.raw.tim_mcgraw_record,
                R.raw.twenty_two_record,
                R.raw.wonderland_record
        };
        mediaPlayer=MediaPlayer.create(this,r[MainActivity.record_idx]);
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