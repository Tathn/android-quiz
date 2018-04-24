package com.example.patryk.quiz.view.questionsList;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.patryk.quiz.R;
import com.example.patryk.quiz.domain.model.Question;
import com.example.patryk.quiz.view.questionView.QuestionView;
import com.example.patryk.quiz.view.questionView.QuestionViewFactory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class QuestionsListFragment extends Fragment {
    private QuestionsListViewModel questionsListViewModel;
    private List<QuestionView> questionViews;
    private LinearLayout questionListContainer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        questionsListViewModel = ViewModelProviders.of(getActivity()).get(QuestionsListViewModel.class);
        questionViews = new ArrayList<>();
        if(questionsListViewModel.hasQuestions()) {
            generateQuestionsViews(questionsListViewModel.getQuestions());
        }
        else {
            new LoadQuestionsViewsTask(this).execute();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.questions_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        questionListContainer = (LinearLayout) view.findViewById(R.id.questionListFragmentContainer);
    }

    public void generateQuestionsViews(Iterable<Question> questions) {
        questions.forEach(question -> {
            questionViews.add(QuestionViewFactory.createQuestionView(getContext(), question));
        });
        questionViews.forEach(questionView -> questionListContainer.addView(questionView));
    }

    public long getAmountOfCorrectAnswers() {
        return questionViews.stream().filter(QuestionView::isAnsweredCorrectly).count();
    }

    public long getAmountOfQuestions() {
        return questionViews.size();
    }

    public void clearAnswers() {
        questionViews.forEach(QuestionView::clearAnswers);
    }

    public void loadNewQuestions() {
        questionListContainer.removeAllViews();
        questionViews = new ArrayList<>();
        new LoadQuestionsViewsTask(this).execute();
    }

    private static class LoadQuestionsViewsTask extends AsyncTask<Void, Void, List<Question>>{

        WeakReference<QuestionsListFragment> fragmentReference;

        public LoadQuestionsViewsTask(QuestionsListFragment context) {
            fragmentReference = new WeakReference<>(context);
        }

        @Override
        protected List<Question> doInBackground(Void... voids) {
            return fragmentReference.get().questionsListViewModel.getQuestions();
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            fragmentReference.get().generateQuestionsViews(questions);
        }
    }
}
