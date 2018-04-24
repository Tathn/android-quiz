package com.example.patryk.quiz;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.patryk.quiz.view.questionsList.QuestionsListFragment;


public class Quiz extends FragmentActivity {
    private QuestionsListFragment questionsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_template);
        Button leftButton = (Button) findViewById(R.id.left_button);
        leftButton.setText(R.string.clear);
        Button rightButton = (Button) findViewById(R.id.right_button);
        rightButton.setText(R.string.submit);
        if (savedInstanceState == null) {
            questionsListFragment = new QuestionsListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.quiz_content, questionsListFragment ).commit();
        } else {
            questionsListFragment = (QuestionsListFragment)getSupportFragmentManager().getFragment(savedInstanceState, "questionsListFragment");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "questionsListFragment", questionsListFragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("OnActivityResult", "Started with req code:" + requestCode + " and resultCode " + resultCode);
        switch(resultCode) {
            case QuizResults.STATUS_CODE_RESTART:
                /*
                    TODO: Allow user to press submit button only when new questions are loaded
                    If user spams Submit/Restart button app can crash because Fragment wants to add Views
                    which still have parents.
                */
                questionsListFragment.loadNewQuestions();
                break;
            case QuizResults.STATUS_CODE_GOING_BACK:
                break;
        }
    }

    public void onButtonClick(View view) {
        switch(view.getId()) {
            case R.id.left_button:
                onClear();
                break;
            case R.id.right_button:
                onSubmit();
                break;
        }
    }

    private void onClear() {
        questionsListFragment.clearAnswers();
    }

    private void onSubmit() {
        Intent showResults = new Intent(this, QuizResults.class);
        showResults.putExtra("amountOfCorrectAnswers", questionsListFragment.getAmountOfCorrectAnswers());
        showResults.putExtra("amountOfQuestions", questionsListFragment.getAmountOfQuestions());
        startActivityForResult(showResults, 0);
    }
}
