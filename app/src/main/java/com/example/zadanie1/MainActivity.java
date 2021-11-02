package com.example.zadanie1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int currentIndex = 0;
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private static final String QUIZ_TAG = "MainActivity";
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG,"Wywołana zostałą metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(QUIZ_TAG,"Zostałą wywołana metoda cyklu życia onCreate");
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onCreate");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia: onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(QUIZ_TAG,"Wywołana została metoda cyklu życia: onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(QUIZ_TAG,"Wywołana zostałą metoda cyklu życia: onDestroy");
    }

    private Question[] questions = new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false),
    };
    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        }
        else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }
}