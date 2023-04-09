package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.util.ArrayList;

/**
 * A command that removes an element from the ArrayList of flashcards.
 */
public class DeleteCommand extends Command {
    ArrayList<Flashcard> matchingFlashcards;
    String query;

    public DeleteCommand(String query) {
        this.matchingFlashcards = new ArrayList<>();
        this.query = query;
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        matchingFlashcards = flashcardList.queryFlashcards(query);

        if (matchingFlashcards.size() == 0) {
            System.out.printf("Sorry! No flashcards matching \"%s\" was found. Please try again.\n",
                    query);
            return;
        }

        System.out.printf("Found %d card(s) with query \"%s\":\n", matchingFlashcards.size(),
                query);
        display.printFlashCards(matchingFlashcards);
        System.out.println("Which one do you want to delete?");

        int index = -1;
        while (index == -1) {
            try {
                index = Integer.parseInt(display.getUserCommand());
                if (index < 1 || index > matchingFlashcards.size()) {
                    index = -1;
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid index value.");
            }
        }

        ArrayList<Flashcard> flashcards = flashcardList.getFlashCards();
        int actualIndex = flashcards.indexOf(matchingFlashcards.get(index - 1));
        flashcardList.deleteFlashcard(actualIndex);
        display.printSuccessfulDelete(index);
    }
}
