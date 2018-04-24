package com.example.patryk.quiz.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Patryk on 2017-12-20.
 */

@Entity( tableName = "answers",
        foreignKeys = @ForeignKey(entity = Question.class,
        parentColumns = "id",
        childColumns = "question_id"))
public class Answer {
    @PrimaryKey( autoGenerate = true )
    private int id;
    private String text;
    private boolean isCorrect;
    @ColumnInfo(name = "question_id")
    private int questionId;

    public Answer(String text, boolean isCorrect, int questionId){
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() {
        return text;
    }
    public void setText(String text) { this.text = text; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    @Override
    public String toString() {
        return "Answer " + id + ": " + text + " " + (isCorrect ? "correct" : "wrong");
    }
}
