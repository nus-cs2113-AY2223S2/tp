package com.clanki.objects;

import java.util.ArrayList;

public class FlashcardList {
    private final ArrayList<Flashcard> flashcards;

    public FlashcardList() {
        this.flashcards = new ArrayList<>();
    }

    public FlashcardList(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    /**
     * Adds a new flashcard into the list.
     *
     * @param questionText Question text of the new flashcard.
     * @param answerText   Answer text of the new flashcard.
     */
    public void addNewFlashcard(String questionText, String answerText) {
        Flashcard newFlashcard = new Flashcard(questionText, answerText);
        flashcards.add(newFlashcard);
    }

    public ArrayList<Flashcard> getFlashCards() {
        return flashcards;
    }

    //@@author javienneyeo
    /**
     * Query for flashcards in the current deck that matches the query inside
     * questions and answers (case-insensitive).
     */
    public ArrayList<Flashcard> queryFlashcards(String query) {
        ArrayList<Flashcard> matchingFlashcards = new ArrayList<>();
        String queryLowerCase = query.toLowerCase();
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard currentFlashcard = flashcards.get(i);
            if (currentFlashcard.getQuestion().toLowerCase().contains(queryLowerCase)
                    || currentFlashcard.getAnswer().toLowerCase().contains(queryLowerCase)) {
                matchingFlashcards.add(currentFlashcard);
            }
        }
        return matchingFlashcards;
    }

    public void deleteFlashcard(int index) {
        flashcards.remove(index);
    }

    public void deleteAllFlashcards() {
        flashcards.clear();
    }
}
