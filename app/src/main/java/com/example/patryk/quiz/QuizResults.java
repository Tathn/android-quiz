package com.example.patryk.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Patryk on 2018-01-19.
 */

public class QuizResults extends Activity {
    public static final int STATUS_CODE_GOING_BACK = 1;
    public static final int STATUS_CODE_RESTART = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_template);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            LinearLayout quizContent = (LinearLayout) findViewById(R.id.quiz_content);
            TextView resultsView =  new TextView(this);
            long amountOfCorrectAnswers = extras.getLong("amountOfCorrectAnswers");
            long amountOfQuestions = extras.getLong("amountOfQuestions");
            String result = getString(R.string.result, amountOfCorrectAnswers, amountOfQuestions);
            resultsView.setText(result);
            quizContent.addView(resultsView);
        }
        Button leftButton = (Button) findViewById(R.id.left_button);
        leftButton.setText(R.string.back);
        Button rightButton = (Button) findViewById(R.id.right_button);
        rightButton.setText(R.string.start_again);
    }

    public void onButtonClick(View view) {
        switch(view.getId()) {
            case R.id.left_button:
                setResult(STATUS_CODE_GOING_BACK);
                break;
            case R.id.right_button:
                setResult(STATUS_CODE_RESTART);
                break;
        }
        finish();
    }
}
