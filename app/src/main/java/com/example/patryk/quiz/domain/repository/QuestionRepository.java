package com.example.patryk.quiz.domain.repository;

import android.util.Log;

import com.example.patryk.quiz.AppExecutors;
import com.example.patryk.quiz.domain.model.AnswerDao;
import com.example.patryk.quiz.domain.model.Question;
import com.example.patryk.quiz.domain.model.QuestionDao;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Patryk on 2017-12-27.
 */

@Singleton
public class QuestionRepository {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final AppExecutors executors;

    @Inject
    public QuestionRepository(QuestionDao questionDao, AnswerDao answerDao, AppExecutors executors) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
        this.executors = executors;
    }

    public List<Question> findAllQuestions(){
        List<Question> questions = Collections.emptyList();
        try {
            FetchAllQuestionsTask fetchAllQuestionsTask = new FetchAllQuestionsTask(questionDao, answerDao);
            Future<List<Question>> futureQuestions = executors.getDiskIO().submit(fetchAllQuestionsTask);
            questions = futureQuestions.get();
            if(questions.size() == 0) {
                Log.d("QuestionRepository:findAllQuestions", "No Questions in local db. Inserting example questions.");
                InsertExampleQuestionsTask insertExampleQuestionsTask = new InsertExampleQuestionsTask(questionDao, answerDao);
                Future<List<Question>> futureExampleQuestions = executors.getDiskIO().submit(insertExampleQuestionsTask);
                questions = futureExampleQuestions.get();
            } else
                Log.d("QuestionRepository:findAllQuestions", "Questions fetched successfully.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
