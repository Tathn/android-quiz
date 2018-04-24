package com.example.patryk.quiz.domain.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Patryk on 2017-12-26.
 */

@Dao
public interface QuestionDao {

    @Insert
    void insertQuestions(List<Question> questions);

    @Query("SELECT COUNT(id) FROM questions")
    int countQuestions();

    @Query("DELETE FROM questions")
    void nukeQuestions();

    @Query("SELECT * FROM questions")
    List<Question> loadAllQuestions();
}
