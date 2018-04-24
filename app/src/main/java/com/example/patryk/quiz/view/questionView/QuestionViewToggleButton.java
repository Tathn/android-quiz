package com.example.patryk.quiz.view.questionView;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.patryk.quiz.domain.model.Question;

/**
 * Created by Patryk on 2018-01-18.
 */

public class QuestionViewToggleButton extends QuestionView{
        private ToggleButton toggleButton;

    public QuestionViewToggleButton(Context context, Question question) {
        super(context, question);
        toggleButton = new ToggleButton(context);
        toggleButton.setLayoutParams(new ViewGroup.LayoutParams(80,60));
        int questionId = question.getId();
        String toggleId = String.valueOf(questionId).concat("0");
        toggleButton.setId(Integer.valueOf(toggleId));
        toggleButton.setTextOn(question.getAnswers().get(0).getText());
        toggleButton.setTextOff(question.getAnswers().get(1).getText());
        toggleButton.setChecked(false);
        addView(toggleButton);
    }

    @Override
    public boolean isAnsweredCorrectly() {
        return toggleButton.isChecked() ? question.getAnswers().get(0).isCorrect() : question.getAnswers().get(1).isCorrect() ;
    }

    @Override
    public void clearAnswers() {
        toggleButton.setChecked(false);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superParcelable", super.onSaveInstanceState());
        bundle.putBoolean("QuestionViewToggleButton".concat(String.valueOf(toggleButton.getId())), toggleButton.isChecked());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;
        super.onRestoreInstanceState(savedState.getParcelable("superParcelable"));
        toggleButton.setChecked(savedState.getBoolean("QuestionViewToggleButton".concat(String.valueOf(toggleButton.getId()))));
    }
}
