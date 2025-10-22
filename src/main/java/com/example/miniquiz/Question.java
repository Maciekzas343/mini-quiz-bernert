package com.example.miniquiz;

public class Question {
    private final String prompt;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String correct;

    public Question(String prompt, String optionA, String optionB, String optionC, String correct) {
        this.prompt = prompt;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correct = correct;
    }

    public String getPrompt() { return prompt; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getCorrect() { return correct; }
}
