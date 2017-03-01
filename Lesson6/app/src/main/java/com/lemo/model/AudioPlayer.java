package com.lemo.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

import com.lemo.lesson6.R;

/**
 * Created by wxl19 on 2017/2/28.
 */

public class AudioPlayer {

    private MediaPlayer mPlayer;
    private static boolean sPause = false;

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            sPause = false;
        }
    }

    public void play(Context c) {
        if (sPause){
            mPlayer.start();
            return;
        }
        stop();
        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mPlayer.start();
        sPause = false;
    }

    public void playVideo(Context context,SurfaceHolder sh){
        stop();
//        mPlayer = new MediaPlayer();
//        Uri uri = Uri.parse("android.resource://"+"com.lemo.lesson6/raw/test");
//        try {
//            mPlayer.setDataSource(context,uri);
//            mPlayer.prepare();
//            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mPlayer.setDisplay(sh);
//            mPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mPlayer = MediaPlayer.create(context,R.raw.test);
        mPlayer.setDisplay(sh);
        mPlayer.start();
    }

    public void pause() {
        if (isPalying()) {
            mPlayer.pause();
            sPause = true;
        }
    }

    public boolean isPalying() {
        return mPlayer != null;
    }
}
