package com.clanki.objects;

import java.time.LocalDate;

public class Flashcard {
    private static final double SPACED_REPETITION_FACTOR = 1.5;
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

    public Flashcard(String questionText, String answerText, LocalDate dueDate, int currentPeriodInDays) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.dueDate = dueDate;
        this.currentPeriodInDays = currentPeriodInDays;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getCurrentPeriodInDays() {
        return currentPeriodInDays;
    }

    public boolean isDueToday() {
        return this.dueDate.isEqual(LocalDate.now());
    }

    /**
     * this function checks if the flashcard is due or overdue
     * Sometimes the user may not clear all flashcards on time, so we may have cards overdue
     *
     * @return true if the card is due or overdue
     */
    public boolean isDueBeforeToday() {
        return !LocalDate.now().isBefore(this.getDueDate());
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
            double newPeriod = this.currentPeriodInDays * SPACED_REPETITION_FACTOR;
            this.currentPeriodInDays = (int) Math.ceil(newPeriod);
        }
        this.dueDate = this.dueDate.plusDays(this.currentPeriodInDays);
    }
}
