package com.lemo.lesson6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lemo.model.AudioPlayer;

/**
 * Created by wxl19 on 2017/2/28.
 */

public class HelloMoonFragment extends Fragment {

    private Button mPlayBtn;
    private Button mStopBtn;

    private AudioPlayer mPlayer = new AudioPlayer();
    private Button mPauseBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello_moon, container, false);
        mPlayBtn = (Button) view.findViewById(R.id.play_btn);
        mStopBtn = (Button) view.findViewById(R.id.stop_btn);
        mPauseBtn = (Button) view.findViewById(R.id.pause_btn);

        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.play(getActivity());
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });

        mPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}
