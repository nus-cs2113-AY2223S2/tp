package com.clanki.objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FlashcardQueue {
    private final Queue<Flashcard> flashcards;

    public FlashcardQueue(ArrayList<Flashcard> flashcards) {
        this.flashcards = new LinkedList<>();
        for (Flashcard flashcard : flashcards) {
            if (flashcard.isDueBeforeToday()) {
                this.flashcards.add(flashcard);
            }
        }
    }

    public void pushFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public Flashcard popFlashcard() {
        return flashcards.poll();
    }

    public boolean isEmpty() {
        return flashcards.isEmpty();
    }

    public int size() {
        return flashcards.size();
    }
}
