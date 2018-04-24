package com.example.patryk.quiz.domain.repository;

import com.example.patryk.quiz.domain.model.AnswerDao;
import com.example.patryk.quiz.domain.model.Question;
import com.example.patryk.quiz.domain.model.QuestionDao;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Patryk on 2018-01-04.
 */

public class FetchAllQuestionsTask implements Callable<List<Question>> {
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public FetchAllQuestionsTask(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public List<Question> call(){
        List<Question> questions = questionDao.loadAllQuestions();
        questions.forEach(q -> {
            q.setAnswers(answerDao.loadQuestionAnswers(q.getId()));
        });
        return questions;
    }
}
