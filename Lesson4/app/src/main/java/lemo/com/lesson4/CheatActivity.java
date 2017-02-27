package lemo.com.lesson4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wxl19 on 2017/2/21.
 */

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "lemo.com.lesson4.answer_is_true";

    public static final String EXTRA_ANSWER_SHOW = "lemo.com.lesson4.answer_shown";

    private Button mShowAnswer;
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: CheatActivity");
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_btn);
                }else {
                    mAnswerTextView.setText(R.string.false_btn);
                }
                setAnswerShownResult(true);
            }
        });

    }
    private void setAnswerShownResult(boolean isAnswerShown){
            Intent intent = new Intent();
            intent.putExtra(EXTRA_ANSWER_SHOW,isAnswerShown);
            setResult(RESULT_OK,intent);

    }
}
