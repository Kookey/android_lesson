package com.example.wxl19.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mFalseButton;
    private Button mTrueButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private boolean mIsCheater;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_mideast,true),
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,false),
            new TrueFalse(R.string.question_asia,true),
    };
    private int mCurrentIndex = 0;
    private Button mPrevButton;
    private Button mcheatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        initView();
        
        //默认显示的问题
        updateQuestion();
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentIndex = mCurrentIndex-1<0?mQuestionBank.length:mCurrentIndex;
                mCurrentIndex = (mCurrentIndex-1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mcheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean answer = mQuestionBank[mCurrentIndex].ismTrueQuestion();

                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answer );
                //startActivity(intent);
                startActivityForResult(intent,0);
            }
        });
    }
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }


    private void initView() {
        mTrueButton = (Button) this.findViewById(R.id.true_button);
        mFalseButton = (Button) this.findViewById(R.id.false_button);
        mNextButton = (Button) this.findViewById(R.id.next_button);
        mPrevButton = (Button) this.findViewById(R.id.prev_button);
        mQuestionTextView = (TextView)this.findViewById(R.id.text_view);
        mcheatButton = (Button) this.findViewById(R.id.cheat);
    }

    private void checkAnswer(boolean checkAnswer){
        boolean trueAnswer = mQuestionBank[mCurrentIndex].ismTrueQuestion();
        int msg = 0;
        if (mIsCheater){
            msg = R.string.judgment_toast;
        }else{
            if (checkAnswer == trueAnswer) {
                msg = R.string.corrent_toast;
            }else{
                msg = R.string.incorrent_toast;
            }
        }
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOW, false);

    }

}
