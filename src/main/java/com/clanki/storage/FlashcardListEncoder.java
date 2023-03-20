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

        encodedFlashcardBuilder.append(" q/").append(flashcard.getQuestion());
        encodedFlashcardBuilder.append(" a/").append(flashcard.getAnswer());

        encodedFlashcardBuilder.append(" d/").append(flashcard.getDueDate().toString());

        encodedFlashcardBuilder.append(" t/").append(flashcard.getCurrentPeriodInDays());

        return encodedFlashcardBuilder.toString();
    }
}
