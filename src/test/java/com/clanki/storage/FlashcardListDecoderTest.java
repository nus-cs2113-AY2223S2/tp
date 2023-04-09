
package com.clanki.storage;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.clanki.storage.FlashcardListDecoder.decodeFlashcardList;
import static com.clanki.storage.FlashcardListEncoder.encodeFlashcardList;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FlashcardListDecoderTest {

    @Test
    void decodeFlashcardList_correctInput_correctOutput() {
        List<String> storedString = new ArrayList<>();
        storedString.add("q/q1 a/a1 d/" + LocalDate.now().toString() + " p/0");
        storedString.add("q/q2 a/a2 d/" + LocalDate.now().toString() + " p/0");
        ArrayList<Flashcard> decodedFlashcardList = decodeFlashcardList(storedString);
        Flashcard testCard1 = decodedFlashcardList.get(0);
        assertEquals("q1", testCard1.getQuestion());
        assertEquals("a1", testCard1.getAnswer());
        assertEquals(LocalDate.now(), testCard1.getDueDate());
        assertEquals(0, testCard1.getCurrentPeriodInDays());
        Flashcard testCard2 = decodedFlashcardList.get(1);
        assertEquals("q2", testCard2.getQuestion());
        assertEquals("a2", testCard2.getAnswer());
        assertEquals(LocalDate.now(), testCard2.getDueDate());
        assertEquals(0, testCard2.getCurrentPeriodInDays());
    }

    @Test
    void decodeAddressBook_encodeInput_decodeOutput() {
        FlashcardList flashcardList = new FlashcardList();
        flashcardList.addNewFlashcard("q1", "a1");
        flashcardList.addNewFlashcard("q2", "a2");
        List<String> encodedResult = encodeFlashcardList(flashcardList);
        ArrayList<Flashcard> decodedFlashcardList = decodeFlashcardList(encodedResult);
        Flashcard testCard1 = decodedFlashcardList.get(0);
        assertEquals("q1", testCard1.getQuestion());
        assertEquals("a1", testCard1.getAnswer());
        assertEquals(LocalDate.now(), testCard1.getDueDate());
        assertEquals(0, testCard1.getCurrentPeriodInDays());
        Flashcard testCard2 = decodedFlashcardList.get(1);
        assertEquals("q2", testCard2.getQuestion());
        assertEquals("a2", testCard2.getAnswer());
        assertEquals(LocalDate.now(), testCard2.getDueDate());
        assertEquals(0, testCard2.getCurrentPeriodInDays());
    }

    @Test
    void decodeAddressBook_correctAndInvalidInput_correctOutput() {
        List<String> storedString = new ArrayList<>();
        storedString.add("q1 a/a1 d/" + LocalDate.now().toString() + " p/0");
        storedString.add("q/q2 a/a2 d/" + LocalDate.now().toString() + " p/0");
        ArrayList<Flashcard> decodedFlashcardList = decodeFlashcardList(storedString);
        assertEquals(1, decodedFlashcardList.size());
        assertEquals("q2", decodedFlashcardList.get(0).getQuestion());
    }

}
