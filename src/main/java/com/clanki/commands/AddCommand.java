package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * A type of command that will instruct to add a new element into the ArrayList
 * of flashcards.
 */
public class AddCommand extends Command {
    String questionText;
    String answerText;

    public AddCommand(String questionText, String answerText) {
        assert !questionText.isEmpty() : "question text should not be empty";
        this.questionText = questionText;
        assert !answerText.isEmpty() : "answer text should not be empty";
        this.answerText = answerText;
    }

    /**
     * For testing purposes only.
     */
    @Override
    public String toString() {
        return "Question to add: " + questionText + " Answer: " + answerText;
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        flashcardList.addNewFlashcard(questionText, answerText);
        display.printSuccessfulAddMessage(questionText, answerText);
    }
}
