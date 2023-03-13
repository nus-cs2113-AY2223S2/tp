package com.clanki.objects;

import java.util.ArrayList;

public class FlashcardList {
    private final ArrayList<Flashcard> flashcards;

    public FlashcardList() {
        this.flashcards = new ArrayList<>();
    }

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
}
