package com.example.wxl19.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wxl19 on 2016/6/2.
 */
public class CheatActivity extends AppCompatActivity {
    public static final String EXTRA_ANSWER_IS_TRUE="com.wangxl19.android.geoquiz.answer_is_true";

    public static final String EXTRA_ANSWER_SHOW = "com.wangxl19.android.geoquiz.answer_show";

    private TextView answerTextView;
    private Button showAnswerButton;
    private boolean answer;
    private static final String TAG = "CheatActivity";
    private TextView apiLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);
        answer = this.getIntent().getBooleanExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, false);
        Log.i(TAG, "answer: "+answer);
        answerTextView = (TextView) this.findViewById(R.id.answerTextView);
        initView();
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "answer: "+answer);
                answerTextView.setText(String.valueOf(answer));
                setAnswerShowResult(true);
            }
        });
    }

    private void setAnswerShowResult(boolean isAnswerShow) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOW,isAnswerShow);
        setResult(RESULT_OK,intent);

    }

    private void initView() {
        showAnswerButton = (Button) this.findViewById(R.id.showAnswerButton);
        apiLevel = (TextView)this.findViewById(R.id.apiLevel);
        int sdkInt = Build.VERSION.SDK_INT;
        apiLevel.setText(String.valueOf(sdkInt));
    }
}
