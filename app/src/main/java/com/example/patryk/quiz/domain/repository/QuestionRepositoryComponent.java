package com.example.patryk.quiz.domain.repository;


import com.example.patryk.quiz.view.questionsList.QuestionsListViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Patryk on 2018-01-03.
 */

@Singleton
@Component(modules = { QuizDatabaseModule.class, QuestionRepositoryModule.class })
public interface QuestionRepositoryComponent {
    void inject(QuestionsListViewModel viewModel);
}
