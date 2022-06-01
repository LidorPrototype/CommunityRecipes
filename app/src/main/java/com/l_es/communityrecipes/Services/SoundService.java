package com.l_es.communityrecipes.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.l_es.communityrecipes.R;
import com.l_es.communityrecipes.Utilities;

public class SoundService extends Service {

    protected MediaPlayer player;
    private boolean isPlaying = false;

    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Utilities.LOG_FLAG, "Service: onCreate");

        SharedPreferences prefs = getSharedPreferences(Utilities.SP_CREATION_TAG, Context.MODE_PRIVATE);
        int raw_music = prefs.getInt(Utilities.SP_MUSIC_SONG, R.raw.order);
        player = MediaPlayer.create(getApplicationContext(), raw_music);
        //player.start(); // no need to call prepare(); create() does that for you
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Utilities.LOG_FLAG, "Service: onStartCommand");
        player.start();
        this.isPlaying = true;
        return Service.START_NOT_STICKY;
    }

    public boolean getIsPlaying(){
        return this.isPlaying;
    }

    public void onStart(Intent intent, int startId) { /* TODO */ }

    public IBinder onUnBind(Intent arg0) { return null; }

    public void onStop() { this.isPlaying = false; }

    public void onPause() { this.isPlaying = false; }

    @Override
    public void onDestroy() {
        this.isPlaying = false;
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() { /* TODO */ }

}