package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A type of command that will instruct to add a new element into the ArrayList
 * of flashcards.
 */
public class AddCommand extends Command {
    private static Logger logger = Logger.getLogger("AddCommand");
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
        logger.log(Level.INFO, "Start to add new flashcard to list of flashcards.");
        flashcardList.addNewFlashcard(questionText, answerText);
        logger.log(Level.INFO, "Start to print when flashcard successfully added.");
        display.printSuccessfulAddMessage(questionText, answerText);
    }
}
