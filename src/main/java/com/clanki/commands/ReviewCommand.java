package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.objects.FlashcardQueue;
import com.clanki.ui.Ui;

public class ReviewCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        FlashcardQueue flashcardQueue = new FlashcardQueue(flashcardList.getFlashCards());
        if (flashcardQueue.isEmpty()) {
            display.printlnSeveralStrings("There are no cards available for review today.");
            return;
        }
        display.printlnSeveralStrings(String
                .format("There are %d cards available for review today.", flashcardQueue.size()));
        while (!flashcardQueue.isEmpty()) {
            display.printlnSeveralStrings("---");
            Flashcard currentFlashcard = flashcardQueue.popFlashcard();
            System.out.printf("Q: %s (ENTER to view answer)", currentFlashcard.getQuestion());
            display.getUserCommand();
            display.printlnSeveralStrings(String.format("A: %s", currentFlashcard.getAnswer()));
            String answerIfCorrect;
            System.out.print("Did you get it right? (y/n) ");
            while (true) {
                answerIfCorrect = display.getUserCommand().toLowerCase();
                if (answerIfCorrect.equals("y")) {
                    display.printlnSeveralStrings("Great, you got it right!");
                    currentFlashcard.updateDueDateAfterCorrectAnswer();
                    break;
                } else if (answerIfCorrect.equals("n")) {
                    display.printlnSeveralStrings("No worries, we will try again later today.");
                    currentFlashcard.updateDueDateAfterIncorrectAnswer();
                    flashcardQueue.pushFlashcard(currentFlashcard);
                    break;
                } else {
                    System.out.print("Please enter 'y' or 'n': (y/n) ");
                }
            }
        }
        display.printlnSeveralStrings("---",
                "Congrats! You have reviewed all the flashcards due today!");
    }
}
