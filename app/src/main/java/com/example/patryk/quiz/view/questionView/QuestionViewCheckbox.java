package com.example.patryk.quiz.view.questionView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.widget.CheckBox;

import com.example.patryk.quiz.domain.model.Answer;
import com.example.patryk.quiz.domain.model.Question;


/**
 * Created by Patryk on 2018-01-18.
 */

@SuppressLint("ViewConstructor")
public class QuestionViewCheckbox extends QuestionView {
    private SparseArray<Answer> checkboxIdToAnswerMap;

    public QuestionViewCheckbox(Context context, Question question) {
        super(context, question);
        checkboxIdToAnswerMap = new SparseArray<>();
        int counter = 0;
        int questionId = question.getId();
        for(Answer answer : question.getAnswers()) {
            CheckBox answerCheckbox = new CheckBox(context);
            String checkboxId = String.valueOf(questionId).concat(String.valueOf(counter++));
            answerCheckbox.setId(Integer.valueOf(checkboxId));
            answerCheckbox.setText(answer.getText());
            checkboxIdToAnswerMap.put(answerCheckbox.getId(), answer);
            addView(answerCheckbox);
        }
    }

    @Override
    public boolean isAnsweredCorrectly() {
        for( int i = 0; i < checkboxIdToAnswerMap.size(); i++) {
                    CheckBox checkBox = (CheckBox)findViewById(checkboxIdToAnswerMap.keyAt(i));
                    Answer answer = checkboxIdToAnswerMap.valueAt(i);
                    if( checkBox.isChecked() && !answer.isCorrect() ||
                            !checkBox.isChecked() && answer.isCorrect())
                        return false;
        }
        return true;
    }

    @Override
    public void clearAnswers() {
        for( int i = 0; i < checkboxIdToAnswerMap.size(); i++) {
            CheckBox checkBox = (CheckBox)findViewById(checkboxIdToAnswerMap.keyAt(i));
            checkBox.setChecked(false);
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superParcelable", super.onSaveInstanceState());
        for(int i = 0; i < checkboxIdToAnswerMap.size(); i++) {
            CheckBox checkbox = (CheckBox) findViewById(checkboxIdToAnswerMap.keyAt(i));
            bundle.putBoolean("QuestionViewCheckbox".concat(String.valueOf(checkbox.getId())),
                    checkbox.isChecked());
        }
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        Bundle savedState = (Bundle) state;
        super.onRestoreInstanceState(savedState.getParcelable("superParcelable"));
        for(int i = 0; i < checkboxIdToAnswerMap.size(); i++) {
            CheckBox checkbox = (CheckBox) findViewById(checkboxIdToAnswerMap.keyAt(i));
            boolean savedCheckboxIsChecked = savedState.getBoolean("QuestionViewCheckbox".concat(String.valueOf(checkbox.getId())));
            checkbox.setChecked(savedCheckboxIsChecked);
        }
    }
}
