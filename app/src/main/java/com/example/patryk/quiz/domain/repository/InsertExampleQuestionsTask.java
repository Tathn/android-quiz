package com.example.patryk.quiz.domain.repository;

import com.example.patryk.quiz.domain.model.Answer;
import com.example.patryk.quiz.domain.model.AnswerDao;
import com.example.patryk.quiz.domain.model.Question;
import com.example.patryk.quiz.domain.model.QuestionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Patryk on 2018-01-04.
 */

public class InsertExampleQuestionsTask implements Callable<List<Question>> {
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public InsertExampleQuestionsTask(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public List<Question> call(){
        ArrayList<Question> exampleQuestions = new ArrayList<>();
        ArrayList<Answer> exampleQuestionsAnswers = new ArrayList<>();
        exampleQuestions.add(new Question("Which class is the superclass of all classes in Java?", Question.Type.RADIO, 1));
        exampleQuestionsAnswers.add(new Answer("java.lang.Entity", false, 1));
        exampleQuestionsAnswers.add(new Answer("java.lang.Object", true, 1));
        exampleQuestionsAnswers.add(new Answer("java.util.Objects", false, 1));
        exampleQuestionsAnswers.add(new Answer("java.persistence.Entity", false, 1));
        exampleQuestions.add(new Question("Does Java support multiple inheritance?", Question.Type.RADIO, 2));
        exampleQuestionsAnswers.add(new Answer("Yes", false, 2));
        exampleQuestionsAnswers.add(new Answer("No", true, 2));
        exampleQuestions.add(new Question("Select all JVM programming languages.", Question.Type.CHECKBOX, 3));
        exampleQuestionsAnswers.add(new Answer("Scala", true, 3));
        exampleQuestionsAnswers.add(new Answer("Swift", false, 3));
        exampleQuestionsAnswers.add(new Answer("Kotlin", true, 3));
        exampleQuestionsAnswers.add(new Answer("Clojure", true, 3));
        exampleQuestions.add(new Question("Select statements that define Java 8 Streams.", Question.Type.CHECKBOX, 4));
        exampleQuestionsAnswers.add(new Answer("Streams can transform data.", true, 4));
        exampleQuestionsAnswers.add(new Answer("A stream is a data structure", false, 4));
        exampleQuestionsAnswers.add(new Answer("Streams can mutate data.", false, 4));
        exampleQuestionsAnswers.add(new Answer("A stream is a pipeline of functions that can be evaluated.", true, 4));
        exampleQuestions.add(new Question("Is java.lang.String immutable?", Question.Type.TOGGLE_BUTTON, 5));
        exampleQuestionsAnswers.add(new Answer("Yes", true, 5));
        exampleQuestionsAnswers.add(new Answer("No", false, 5));
        exampleQuestions.add(new Question("Can we overload operators in Java?", Question.Type.TOGGLE_BUTTON, 6));
        exampleQuestionsAnswers.add(new Answer("Yes", false, 6));
        exampleQuestionsAnswers.add(new Answer("No", true, 6));
        questionDao.insertQuestions(exampleQuestions);
        answerDao.insertAnswers(exampleQuestionsAnswers);

        List<Question> questions = questionDao.loadAllQuestions();
        questions.forEach(q -> {
            q.setAnswers(answerDao.loadQuestionAnswers(q.getId()));
        });
        return questions;
    }
}
