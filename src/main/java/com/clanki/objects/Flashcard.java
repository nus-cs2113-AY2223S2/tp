package com.clanki.objects;

import java.time.LocalDate;

public class Flashcard {
    private final double SPACED_REPETITION_FACTOR = 1.5;
    private String questionText;
    private String answerText;
    private LocalDate dueDate;
    private int currentPeriodInDays;

    public Flashcard(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.dueDate = LocalDate.now();
        this.currentPeriodInDays = 0;
    }

    public String getQuestion() {
        return questionText;
    }

    public void setQuestion(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answerText;
    }

    public void setAnswer(String answerText) {
        this.answerText = answerText;
    }

    public boolean isDueToday() {
        return this.dueDate.isEqual(LocalDate.now());
    }

    /**
     * Reset <code>dueDate</code> and <code>currentPeriodInDays</code> as if the
     * card was just created.
     */
    public void updateDueDateAfterIncorrectAnswer() {
        this.dueDate = LocalDate.now();
        this.currentPeriodInDays = 0;
    }

    /**
     * Update <code>dueDate</code> and <code>currentPeriodInDays</code> according to
     * the spaced repetition algorithm. Which is very simple, to be honest, but it
     * does the job.
     */
    public void updateDueDateAfterCorrectAnswer() {
        if (this.currentPeriodInDays == 0) {
            this.currentPeriodInDays = 1;
        } else {
            this.currentPeriodInDays *= SPACED_REPETITION_FACTOR;
        }
        this.dueDate = this.dueDate.plusDays(this.currentPeriodInDays);
    }
}
