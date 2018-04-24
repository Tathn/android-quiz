package com.example.patryk.quiz.view.questionsList;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.patryk.quiz.domain.model.Question;
import com.example.patryk.quiz.domain.repository.DaggerQuestionRepositoryComponent;
import com.example.patryk.quiz.domain.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Patryk on 2017-12-29.
 */

public class QuestionsListViewModel extends ViewModel {

    @Inject
    QuestionRepository questionRepository;

    private List<Question> quizQuestions;

    public QuestionsListViewModel(){
        Log.d("QuestionsListViewModel", "repo injected");
        DaggerQuestionRepositoryComponent.create().inject(this);
        quizQuestions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        if(quizQuestions.isEmpty())
            quizQuestions = questionRepository.findAllQuestions();
        return quizQuestions;
    }

    public boolean hasQuestions() {
        return !quizQuestions.isEmpty();
    }
}
