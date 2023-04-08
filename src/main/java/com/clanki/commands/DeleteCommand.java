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

    private static final String FLASHCARD_TO_DELETE = "Which one do you want to delete?";
    ArrayList<Flashcard> matchingFlashcards = new ArrayList<>();
    String query;

    public DeleteCommand(String query) {
        this.query = query;
    }

    /**
     * Search through flashcards containing the query and adds them to the matchingFlashcards list.
     *
     * @param flashcards the list of flashcards to look through
     * @param query      the search query for finding matching flashcards
     */
    public void findFlashcard(ArrayList<Flashcard> flashcards, String query) {
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard currentFlashcard = flashcards.get(i);
            if (currentFlashcard.getQuestion().contains(query)
                    || currentFlashcard.getAnswer().contains(query)) {
                matchingFlashcards.add(currentFlashcard);
            }
        }
    }

    /**
     * Prints out the question and answer for each flashcard
     *
     * @param flashcard each individual flashcard in the ArrayList
     */
    public void printFlashCard(Flashcard flashcard) {
        System.out.println("Q: " + flashcard.getQuestion());
        System.out.println("A: " + flashcard.getAnswer());
    }

    /**
     * Goes through the ArrayList of flashcards and prints out each one of them
     *
     * @param flashcards the ArrayList of flashcards
     */
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
            System.out.println(FLASHCARD_TO_DELETE);

            int index = Integer.parseInt(display.getUserCommand());
            flashcardList.deleteFlashcard(flashcards.indexOf(matchingFlashcards.get(index - 1)));
            display.printSuccessfulDelete(index);
        } else {
            System.out.println("Sorry! No flashcards matching \"" + query + "\" was found. Please try again.");
        }
    }
}
