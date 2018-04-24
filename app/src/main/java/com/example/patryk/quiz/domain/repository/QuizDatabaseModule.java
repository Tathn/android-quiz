package com.example.patryk.quiz.domain.repository;

import android.arch.persistence.room.Room;

import com.example.patryk.quiz.QuizApplication;
import com.example.patryk.quiz.domain.model.AnswerDao;
import com.example.patryk.quiz.domain.model.QuestionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Patryk on 2018-01-01.
 */

@Singleton
@Module
public class QuizDatabaseModule {

    private static QuizDatabase quizDatabase;

    private static QuizDatabase getQuizDatabase() {
        if(quizDatabase == null) {
            quizDatabase = Room.databaseBuilder(QuizApplication.getContext(),
                    QuizDatabase.class, "quiz_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return quizDatabase;
    }
    @Provides public static QuestionDao provideQuestionDao() {
        return getQuizDatabase().questionDao();
    }

    @Provides public static AnswerDao provideAnswerDao() {
        return getQuizDatabase().answerDao();
    }
}
