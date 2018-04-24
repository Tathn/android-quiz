package com.example.patryk.quiz.domain.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.patryk.quiz.domain.model.Answer;
import com.example.patryk.quiz.domain.model.AnswerDao;
import com.example.patryk.quiz.domain.model.Question;
import com.example.patryk.quiz.domain.model.QuestionDao;


/**
 * Created by Patryk on 2017-12-26.
 */

@Database(version = 7, entities = {Question.class, Answer.class})
@TypeConverters(Question.Type.class)
abstract class QuizDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();
}
