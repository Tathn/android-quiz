package com.example.patryk.quiz.domain.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Patryk on 2017-12-27.
 */
@Dao
public interface AnswerDao {

    @Insert
    void insertAnswers(List<Answer> answerList);

    @Query("SELECT * FROM answers WHERE question_id = :questionId")
    List<Answer> loadQuestionAnswers(int questionId);

    @Query("DELETE FROM answers")
    void nukeAnswers();
}
