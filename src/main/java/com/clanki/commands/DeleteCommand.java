package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.util.ArrayList;

/**
 * A command that removes an element from the ArrayList of
 * flashcards.
 */
public class DeleteCommand extends Command {

    ArrayList<Flashcard> matchingFlashcards = new ArrayList<>();
    String query;

    public DeleteCommand(String query) {
        this.query = query;
    }

    public void findFlashcard(ArrayList<Flashcard> flashcards, String query) {
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard currentFlashcard = flashcards.get(i);
            if (currentFlashcard.getQuestion().contains(query)
                    || currentFlashcard.getAnswer().contains(query)) {
                matchingFlashcards.add(currentFlashcard);
            }
        }
    }

    public void printFlashCard(Flashcard flashcard) {
        System.out.println("Q: " + flashcard.getQuestion());
        System.out.println("A: " + flashcard.getAnswer());
    }

    public void printFlashCardList(ArrayList<Flashcard> flashcards) {
        for (int i = 0; i < flashcards.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printFlashCard(flashcards.get(i));
        }
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashCards();
        findFlashcard(flashcards, query);
        if (matchingFlashcards.size() != 0) {
            System.out.println(
                    "Found " + matchingFlashcards.size() + " cards with query \"" + query + "\":");
            printFlashCardList(matchingFlashcards);
            System.out.println("Which one do you want to delete?");

            int index = Integer.parseInt(display.getUserCommand());
            flashcardList.deleteFlashcard(flashcards.indexOf(matchingFlashcards.get(index - 1)));
            display.printSuccessfulDelete(index);
        } else {
            System.out.println("Sorry! No flashcards matching " + '\"' + query + "\" was found. Please try again.");
        }


    }
}
