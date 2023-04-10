
package com.clanki.storage;

import com.clanki.objects.FlashcardList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.clanki.storage.FlashcardListEncoder.encodeFlashcardList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlashcardListEncoderTest {

    @Test
    void encodeAddressBook_correctInput_correctOutput() {
        FlashcardList flashcardList = new FlashcardList();
        flashcardList.addNewFlashcard("q1", "a1");
        flashcardList.addNewFlashcard("q2", "a2");
        List<String> encodedResult = encodeFlashcardList(flashcardList);
        String line1 = "q/q1 a/a1 d/" + LocalDate.now().toString() + " p/0";
        String line2 = "q/q2 a/a2 d/" + LocalDate.now().toString() + " p/0";
        assertEquals(line1, encodedResult.get(0));
        assertEquals(line2, encodedResult.get(1));
    }

    @Test
    void encodeAddressBook_emptyInput() {
        FlashcardList flashcardList = new FlashcardList();
        List<String> encodedResult = encodeFlashcardList(flashcardList);
        assertTrue(encodedResult.isEmpty());
    }

}
