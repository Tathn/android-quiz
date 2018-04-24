package com.example.patryk.quiz.view.questionView;

import android.content.Context;

import com.example.patryk.quiz.domain.model.Question;

/**
 * Created by Patryk on 2018-01-08.
 */

public class QuestionViewFactory {

    public static QuestionView createQuestionView(Context context, Question question) {
        switch (question.getType()) {
            case RADIO: return new QuestionViewRadio(context,question);
            case CHECKBOX: return new QuestionViewCheckbox(context,question);
            case TOGGLE_BUTTON: return new QuestionViewToggleButton(context,question);
            default:
                throw new IllegalArgumentException("Invalid Question type.");
        }
    }
}
