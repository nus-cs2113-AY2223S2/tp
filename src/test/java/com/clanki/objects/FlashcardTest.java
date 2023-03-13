
package com.clanki.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

class FlashcardTest {
    @Test
    public void constructNewFlashcard_correctlyFormattedInputs_success() {
        Flashcard testCard = new Flashcard("QUESTION", "ANSWER");
        assertEquals("QUESTION", testCard.getQuestion());
        assertEquals("ANSWER", testCard.getAnswer());
        assertEquals(LocalDate.now(), testCard.getDueDate());
        assertEquals(0, testCard.getCurrentPeriodInDays());
        assertEquals(true, testCard.isDueToday());
    }

    @Test
    public void updateFlashcard_incorrectAnswer_shouldResetDatesToToday() {
        Flashcard testCard = new Flashcard("QUESTION", "ANSWER", LocalDate.now(), 1);
        testCard.updateDueDateAfterIncorrectAnswer();
        assertEquals(LocalDate.now(), testCard.getDueDate());
        assertEquals(0, testCard.getCurrentPeriodInDays());
    }

    @Test
    public void updateFlashcard_correctAnswer_shouldUpdateDates() {
        Flashcard testCard = new Flashcard("q1", "a1");
        testCard.updateDueDateAfterCorrectAnswer();
        assertEquals(LocalDate.now().plusDays(1), testCard.getDueDate());
        assertEquals(1, testCard.getCurrentPeriodInDays());

        testCard = new Flashcard("q2", "a2", LocalDate.now(), 1);
        testCard.updateDueDateAfterCorrectAnswer();
        assertEquals(LocalDate.now().plusDays(2), testCard.getDueDate());
        assertEquals(2, testCard.getCurrentPeriodInDays());

        testCard = new Flashcard("q3", "a3", LocalDate.now(), 2);
        testCard.updateDueDateAfterCorrectAnswer();
        assertEquals(LocalDate.now().plusDays(3), testCard.getDueDate());
        assertEquals(3, testCard.getCurrentPeriodInDays());

        testCard = new Flashcard("q4", "a4", LocalDate.now(), 45);
        testCard.updateDueDateAfterCorrectAnswer();
        assertEquals(LocalDate.now().plusDays(68), testCard.getDueDate());
        assertEquals(68, testCard.getCurrentPeriodInDays());
    }
}
