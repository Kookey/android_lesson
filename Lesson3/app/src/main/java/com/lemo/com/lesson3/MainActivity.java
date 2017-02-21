package com.lemo.com.lesson3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String KEY_INDEX =  "index";

    private Button trueBtn;
    private Button falseBtn;
    private ImageButton nextBtn;
    private ImageButton preBtn;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia,true)
    };

    private int mCurrentIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        setContentView(R.layout.activity_main);
        trueBtn = (Button) findViewById(R.id.btn_true);
        falseBtn = (Button) findViewById(R.id.btn_false);
        nextBtn =(ImageButton) findViewById(R.id.next_btn);
        preBtn = (ImageButton)findViewById(R.id.prev_btn);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        TrueFalse trueFalse = mQuestionBank[mCurrentIndex];
        mQuestionTextView.setText(trueFalse.getQuestion());
        updateQuestion();
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex==0){
                    mCurrentIndex  = mQuestionBank.length;
                }
                mCurrentIndex = (mCurrentIndex-1)%mQuestionBank.length;
                updateQuestion();
            }
        });


        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    /**
     * 更新TextView视图
     */
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        int messageId = 0;
        boolean trueQuestion = mQuestionBank[mCurrentIndex].isTrueQuestion();
        if(userPressedTrue==trueQuestion){
            messageId = R.string.correct;
        }else{
            messageId = R.string.incorrect;
        }
        Toast.makeText(MainActivity.this,messageId,Toast.LENGTH_LONG).show();
    }

    /**
     *
     * onSaveInstanceState 在onPause,onStop,onDestory 方法之前调用
     * onSaveInstanceState 中如果要保存对象，被保存的对象必须实现Serializable
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }
}
