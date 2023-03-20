package com.clanki.storage;

import com.clanki.exceptions.StorageOperationException;
import com.clanki.objects.Flashcard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class modifies the list of flashcards from stored text into an ArrayList in FlashcardList.
 * Many portions of the code in this class are modified from the link below: (addressbook-level2)
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
 */
public class FlashcardListDecoder {
    public static final Pattern FLASHCARD_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("q/(?<question>[^/]+)" + " a/(?<answer>[^/]+)"
                    + " d/(?<dueDate>[^/]+)"
                    + " p/(?<currentPeriod>[^/]+)");


    /**
     * Decodes {@code encodedFlashcardList} into an {@code ArrayList<Flashcard>} containing the decoded flashcards.
     *
     * @throws StorageOperationException if the {@code encodedFlashcardList} is in an invalid format.
     */
    public static ArrayList<Flashcard> decodeFlashcardList(List<String> encodedFlashcardList)
            throws StorageOperationException {
        final ArrayList<Flashcard> decodedFlashcard = new ArrayList<>();
        for (String encodedFlashcard : encodedFlashcardList) {
            decodedFlashcard.add(decodeFlashcardFromString(encodedFlashcard));
        }
        return decodedFlashcard;
    }

    /**
     * Decodes {@code encodedFlashcard} into a {@code Flashcard}.
     *
     * @throws StorageOperationException if {@code encodedFlashcard} is in an invalid format.
     */
    private static Flashcard decodeFlashcardFromString(String encodedFlashcard)
            throws StorageOperationException {
        final Matcher matcher = FLASHCARD_ARGS_FORMAT.matcher(encodedFlashcard);
        if (!matcher.matches()) {
            throw new StorageOperationException("Encoded person in invalid format. Unable to decode.");
        }

        return new Flashcard(matcher.group("question"), matcher.group("answer"),
                LocalDate.parse(matcher.group("dueDate")),
                Integer.parseInt(matcher.group("currentPeriod"))
        );
    }


}
