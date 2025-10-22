package com.example.miniquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView titleText, questionText, scoreText;
    private Button startButton, answerAButton, answerBButton, answerCButton, resetButton;

    // Pełna pula pytań (możesz dodać więcej)
    private final List<Question> questionBank = new ArrayList<>();

    // Bieżąca rozgrywka:
    private List<Question> currentQuiz = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;
    private final int QUESTIONS_PER_GAME = 5;

    private boolean quizRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        buildQuestionBank();
        setupUiInitialState();
        setupListeners();
    }

    private void bindViews() {
        titleText = findViewById(R.id.titleText);
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        startButton = findViewById(R.id.startButton);
        answerAButton = findViewById(R.id.answerAButton);
        answerBButton = findViewById(R.id.answerBButton);
        answerCButton = findViewById(R.id.answerCButton);
        resetButton = findViewById(R.id.resetButton);
    }

    private void buildQuestionBank() {
        // przykładowe pytania (PL)
        questionBank.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questionBank.add(new Question("Ile to 3 * 4?", "7", "12", "14", "12"));
        questionBank.add(new Question("Język Android (domyślny kiedyś):", "Swift", "Java", "Kotlin tylko", "Java"));
        questionBank.add(new Question("Ocean przy Kalifornii:", "Atlantycki", "Spokojny (Pacyfik)", "Indyjski", "Spokojny (Pacyfik)"));
        questionBank.add(new Question("HTML to:", "Język programowania", "Język znaczników", "Baza danych", "Język znaczników"));
        questionBank.add(new Question("2^5 to:", "32", "16", "25", "32"));
        questionBank.add(new Question("Stolica Polski:", "Warszawa", "Kraków", "Gdańsk", "Warszawa"));
        questionBank.add(new Question("Kolor po zmieszaniu czerwonego i niebieskiego:", "Zielony", "Fioletowy", "Pomarańczowy", "Fioletowy"));
    }

    private void setupUiInitialState() {
        // Start: tylko tytuł, start, wynik=0
        questionText.setVisibility(View.GONE);
        answerAButton.setVisibility(View.GONE);
        answerBButton.setVisibility(View.GONE);
        answerCButton.setVisibility(View.GONE);

        score = 0;
        updateScore();
        quizRunning = false;
        startButton.setEnabled(true);
    }

    private void setupListeners() {
        startButton.setOnClickListener(v -> startQuiz());

        answerAButton.setOnClickListener(v -> handleAnswer(((Button) v).getText().toString()));
        answerBButton.setOnClickListener(v -> handleAnswer(((Button) v).getText().toString()));
        answerCButton.setOnClickListener(v -> handleAnswer(((Button) v).getText().toString()));

        resetButton.setOnClickListener(v -> {
            score = 0;
            updateScore();
            setupUiInitialState();
            Toast.makeText(this, "Zresetowano. Możesz rozpocząć ponownie.", Toast.LENGTH_SHORT).show();
        });
    }

    private void startQuiz() {
        if (questionBank.size() < QUESTIONS_PER_GAME) {
            Toast.makeText(this, "Za mało pytań w puli!", Toast.LENGTH_SHORT).show();
            return;
        }
        // losuj 5 różnych pytań
        List<Question> shuffled = new ArrayList<>(questionBank);
        Collections.shuffle(shuffled);
        currentQuiz = shuffled.subList(0, QUESTIONS_PER_GAME);

        currentIndex = 0;
        score = 0;
        updateScore();

        quizRunning = true;
        startButton.setEnabled(false);

        // pokaż elementy quizu
        questionText.setVisibility(View.VISIBLE);
        answerAButton.setVisibility(View.VISIBLE);
        answerBButton.setVisibility(View.VISIBLE);
        answerCButton.setVisibility(View.VISIBLE);

        showCurrentQuestion();
    }

    private void showCurrentQuestion() {
        if (currentIndex >= currentQuiz.size()) {
            endQuiz();
            return;
        }
        Question q = currentQuiz.get(currentIndex);
        questionText.setText(q.getPrompt());
        answerAButton.setText(q.getOptionA());
        answerBButton.setText(q.getOptionB());
        answerCButton.setText(q.getOptionC());
    }

    private void handleAnswer(String chosen) {
        if (!quizRunning) return;

        Question q = currentQuiz.get(currentIndex);
        if (chosen.equals(q.getCorrect())) {
            score++;
            updateScore();
            Toast.makeText(this, "Dobrze!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Źle! Poprawna: " + q.getCorrect(), Toast.LENGTH_SHORT).show();
        }

        currentIndex++;
        if (currentIndex < currentQuiz.size()) {
            showCurrentQuestion();
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        quizRunning = false;
        Toast.makeText(this, "Koniec quizu! Twój wynik: " + score + " / " + QUESTIONS_PER_GAME, Toast.LENGTH_LONG).show();
        startButton.setEnabled(true);
        // Możesz schować pytania, jeśli chcesz:
        // questionText.setVisibility(View.GONE);
        // answerAButton.setVisibility(View.GONE);
        // answerBButton.setVisibility(View.GONE);
        // answerCButton.setVisibility(View.GONE);
    }

    private void updateScore() {
        scoreText.setText("Wynik: " + score);
    }
}
