package com.example.patryk.quiz.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Patryk on 2017-12-24.
 */

@Entity( tableName = "questions" )
public class Question {

    public enum Type {
        RADIO,
        CHECKBOX,
        TOGGLE_BUTTON;

        @TypeConverter
        public static Type fromString(String questionType) {
            return Type.valueOf(questionType);
        }

        @TypeConverter
        public static String typeToString(Type questionType) {
            return questionType.name();
        }
    }

    @PrimaryKey( autoGenerate = true )
    private int id;
    private String text;
    private Type type;
    @Ignore
    private List<Answer> answers;

    public Question(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    @Ignore
    public Question(String text, Type type, int id) {
        this(text,type);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    @Override
    public String toString() {
        return "Question " + id + ": " + text + "\n" + answers.stream().map(Answer::toString).collect(Collectors.joining("\n"));
    }
}
