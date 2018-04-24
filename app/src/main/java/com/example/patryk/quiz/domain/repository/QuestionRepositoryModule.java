package com.example.patryk.quiz.domain.repository;

import com.example.patryk.quiz.AppExecutors;
import com.example.patryk.quiz.domain.model.AnswerDao;
import com.example.patryk.quiz.domain.model.QuestionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Patryk on 2018-01-04.
 */

@Singleton
@Module
public class QuestionRepositoryModule {
    @Provides
    public QuestionRepository getQuestionRepository(QuestionDao questionDao, AnswerDao answerDao, AppExecutors executors) {
        return new QuestionRepository(questionDao, answerDao, executors);
    }

}
