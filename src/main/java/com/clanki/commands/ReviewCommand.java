package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.util.ArrayList;

public class ReviewCommand extends Command {
    private static void reviewCurrentFlashcard(Ui display, Flashcard flashcard) {
        boolean isCurrentCardPassed = false;
        display.printlnSeveralStrings("Let's see the next flashcard!");
        while (!isCurrentCardPassed) {
            display.printlnSeveralStrings("Question for the current flashcard is:", flashcard.getQuestion());
            display.printlnSeveralStrings("Are you ready to view the answer?");
            display.getUserCommand();
            display.printlnSeveralStrings(flashcard.getAnswer(), "Have you got this correct?");
            String answerIfCorrect = display.getUserCommand();
            if (answerIfCorrect.equals("yes") || answerIfCorrect.equals("y")) {
                display.printlnSeveralStrings("Great, you got it right!");
                flashcard.updateDueDateAfterCorrectAnswer();
                isCurrentCardPassed = true;
            } else if (answerIfCorrect.equals("n")) {
                display.printlnSeveralStrings("No worries, we will try it next time.");
                flashcard.updateDueDateAfterIncorrectAnswer();
                isCurrentCardPassed = true;
            } else {
                display.printInvalidInput();
            }

        }
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        ArrayList<Flashcard> fullFlashcardList = flashcardList.getFlashCards();
        for (int i = 0; i < fullFlashcardList.size(); i++) {
            Flashcard flashcard = fullFlashcardList.get(i);
            if (flashcard.isDueBeforeToday()) {
                reviewCurrentFlashcard(display, flashcard);
            }
        }
        display.printlnSeveralStrings("Congrats! You have reviewed all the flashcards due today!");
    }
}
