package com.example.patryk.quiz.view.questionView;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patryk.quiz.domain.model.Question;

/**
 * Created by Patryk on 2018-01-08.
 */

public abstract class QuestionView extends LinearLayout {
    protected Question question;

    protected QuestionView(Context context, Question question) {
        super(context);
        this.question = question;
        setId(0x00100000 + question.getId());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 8, 0, 0);
        setLayoutParams(layoutParams);
        setOrientation(LinearLayout.VERTICAL);
        TextView questionText = new TextView(context);
        questionText.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        questionText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        questionText.setText(question.getText());
        addView(questionText);
    }

    public abstract boolean isAnsweredCorrectly();
    public abstract void clearAnswers();

}
