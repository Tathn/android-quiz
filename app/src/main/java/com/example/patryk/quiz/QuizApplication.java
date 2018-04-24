package com.example.patryk.quiz;

import android.app.Application;
import android.content.Context;

/**
 * Created by Patryk on 2018-01-01.
 */

public class QuizApplication extends Application {
    private static QuizApplication quizApplication;

    public QuizApplication(){
        super();
        quizApplication = this;
    }

    public static Context getContext() {
        return quizApplication;
    }
}
