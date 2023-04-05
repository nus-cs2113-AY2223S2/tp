package com.clanki.storage;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class modifies the list of flashcards from FlashcardList into a storable format.
 * Many portions of the code in this class are modified from the link below: (addressbook-level2)
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java
 */
public class FlashcardListEncoder {

    public static final String QUESTION_APPEND = "q/";
    public static final String ANSWER_APPEND = " a/";
    public static final String DUE_DATE_APPEND = " d/";
    public static final String PERIOD_APPEND = " p/";

    /**
     * Encodes all the {@code Flashcards} in the {@code toSave} into a list of decodable and readable
     * string presentation for storage.
     */
    public static List<String> encodeAddressBook(FlashcardList toSave) {
        final List<String> encodedFlashcards = new ArrayList<>();
        toSave.getFlashCards().forEach(flashcard -> encodedFlashcards.add(encodeFlashcardToString(flashcard)));
        return encodedFlashcards;
    }

    /**
     * Encodes the {@code flashcard} into a decodable and readable string representation.
     */
    private static String encodeFlashcardToString(Flashcard flashcard) {
        final StringBuilder encodedFlashcardBuilder = new StringBuilder();

        encodedFlashcardBuilder.append(QUESTION_APPEND).append(flashcard.getQuestion());
        encodedFlashcardBuilder.append(ANSWER_APPEND).append(flashcard.getAnswer());

        encodedFlashcardBuilder.append(DUE_DATE_APPEND).append(flashcard.getDueDate().toString());

        encodedFlashcardBuilder.append(PERIOD_APPEND).append(flashcard.getCurrentPeriodInDays());

        return encodedFlashcardBuilder.toString();
    }
}
