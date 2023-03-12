package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.util.ArrayList;
import java.util.Objects;

public class ReviewCommand extends Command {
    private static void reviewCurrentFlashcard(Ui display, Flashcard flashcard) {
        boolean isCurrentCardPassed = false;
        display.print("Let's see the next flashcard!");
        while (!isCurrentCardPassed) {
            display.print("Question for the current flashcard is: ", flashcard.getQuestion());
            display.print("Are you ready to view the answer?");
            String viewAnswerCommand = display.getUserCommand();
            if (Objects.equals(viewAnswerCommand, "answer") || Objects.equals(viewAnswerCommand, "a")) {
                display.print(flashcard.getAnswer(), "Have you got this correct?");
                String ifCorrectAnswerCommand = display.getUserCommand();
                if (Objects.equals(ifCorrectAnswerCommand, "yes") || Objects.equals(ifCorrectAnswerCommand, "y")) {
                    display.print("Great, you got it right!");
                    flashcard.updateDueDateAfterCorrectAnswer();
                    isCurrentCardPassed = true;
                } else if (Objects.equals(ifCorrectAnswerCommand, "n")) {
                    display.print("No worries, we will try it next time.");
                    flashcard.updateDueDateAfterIncorrectAnswer();
                    isCurrentCardPassed = true;
                } else {
                    display.printInvalidInput();
                }
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
        display.print("Congrats! You have reviewed all the flashcards due today!");
    }
}
