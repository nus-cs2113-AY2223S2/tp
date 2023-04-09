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

    public void deleteFlashcard(int index) {
        flashcards.remove(index);
    }

    public ArrayList<Flashcard> getFlashCards() {
        return flashcards;
    }

    public void deleteAllFlashcards() {
        flashcards.clear();
    }
}
