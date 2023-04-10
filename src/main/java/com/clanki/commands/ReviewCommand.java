package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.objects.FlashcardQueue;
import com.clanki.ui.Ui;

/**
 * A type of command that will let user review the flashcards due today
 */
public class ReviewCommand extends Command {

    public static final String CORRECT_ANSWER_STRING = "y";
    public static final String INCORRECT_ANSWER_STRING = "n";
    public static final String LINE_SEPARATOR = "---";

    /**
     * review the next flashcard in the queue, and update the due date of the flashcard accordingly
     *
     * @param display        Ui object used for user interfacing
     * @param flashcardQueue Queue object storing flashcards to be reviewed
     */
    private static void reviewNextFlashcard(Ui display, FlashcardQueue flashcardQueue) {
        display.printlnSeveralStrings(LINE_SEPARATOR);
        Flashcard currentFlashcard = flashcardQueue.popFlashcard();
        System.out.printf("Q: %s (ENTER to view answer)", currentFlashcard.getQuestion());
        display.getUserCommand();
        display.printlnSeveralStrings(String.format("A: %s", currentFlashcard.getAnswer()));
        String answerIfCorrect;
        System.out.print("Did you get it right? (y/n) ");
        while (true) {
            answerIfCorrect = display.getUserCommand().toLowerCase();
            if (answerIfCorrect.equals(CORRECT_ANSWER_STRING)) {
                display.printlnSeveralStrings("Great, you got it right!");
                currentFlashcard.updateDueDateAfterCorrectAnswer();
                break;
            } else if (answerIfCorrect.equals(INCORRECT_ANSWER_STRING)) {
                display.printlnSeveralStrings("No worries, we will try again later today.");
                currentFlashcard.updateDueDateAfterIncorrectAnswer();
                flashcardQueue.pushFlashcard(currentFlashcard);
                break;
            } else {
                System.out.print("Please enter 'y' or 'n': (y/n) ");
            }
        }
    }

    /**
     * Review all the flashcards that are due today, and update their due dates accordingly
     *
     * @param flashcardList List of all flashcards, regardless of their due dates
     * @param display       Ui object used for user interfacing
     */
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        // Get a queue of flashcards that are due today
        FlashcardQueue flashcardQueue = new FlashcardQueue(flashcardList.getFlashCards());
        
        if (flashcardQueue.isEmpty()) {
            display.printlnSeveralStrings("There are no cards available for review today.");
            return;
        }
        display.printlnSeveralStrings(String
                .format("There are %d cards available for review today.", flashcardQueue.size()));
        while (!flashcardQueue.isEmpty()) {
            reviewNextFlashcard(display, flashcardQueue);
        }
        display.printlnSeveralStrings(LINE_SEPARATOR,
                "Congrats! You have reviewed all the flashcards due today!");
    }
}
