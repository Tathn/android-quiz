package com.example.patryk.quiz.view.questionView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.patryk.quiz.domain.model.Answer;
import com.example.patryk.quiz.domain.model.Question;


/**
 * Created by Patryk on 2018-01-08.
 */

@SuppressLint("ViewConstructor")
public class QuestionViewRadio extends QuestionView {
    private RadioGroup answersRadioGroup;
    private SparseArray<Answer> radioButtonIdToAnswerMap;

    public QuestionViewRadio(Context context, Question question) {
        super(context, question);
        answersRadioGroup = new RadioGroup(context);
        radioButtonIdToAnswerMap = new SparseArray<>();
        int counter = 0;
        int questionId = question.getId();
        for(Answer answer : question.getAnswers()) {
            RadioButton answerButton = new RadioButton(context);
            answerButton.setText(answer.getText());
            String radioButtonId = String.valueOf(questionId).concat(String.valueOf(counter++));
            answerButton.setId(Integer.valueOf(radioButtonId));
            answersRadioGroup.addView(answerButton);
            radioButtonIdToAnswerMap.put(answerButton.getId(), answer);
        };
        addView(answersRadioGroup);
    }

    @Override
    public boolean isAnsweredCorrectly(){
        boolean answerCorrect = false;
        int checkedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId();
        if( checkedRadioButtonId != -1) {
            answerCorrect = radioButtonIdToAnswerMap.get(checkedRadioButtonId).isCorrect();
        }
        return answerCorrect;
    }

    @Override
    public void clearAnswers() {
        answersRadioGroup.clearCheck();
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superParcelable", super.onSaveInstanceState());
        bundle.putInt("QuestionViewRadio".concat(String.valueOf(getId())), answersRadioGroup.getCheckedRadioButtonId());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;
        super.onRestoreInstanceState(savedState.getParcelable("superParcelable"));
        int checkedButtonId = savedState.getInt("QuestionViewRadio".concat(String.valueOf(getId())), -1);
        answersRadioGroup.check(checkedButtonId);
    }

}
