
package com.clanki.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashcardTest {
    @Test
    public void constructNewFlashcard_correctlyFormattedInputs_success() {
        Flashcard testCard = new Flashcard("QUESTION", "ANSWER");
        assertEquals("QUESTION", testCard.getQuestionText());
        assertEquals("ANSWER", testCard.getAnswerText());
    }
}
