package com.example.teo.exfebejercicio3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;

import java.io.IOException;

/**
 * Created by Teo on 06/03/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MediaPlayer mp;
        MediaController mc;
        Log.i("AlarmReceiver","Preparando para que suene");
        mp = new MediaPlayer();
        mp.reset();

        try {
            mp.setDataSource(context, Uri.parse("https://www.w3schools.com/html/horse.ogg"));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();


    }
}
