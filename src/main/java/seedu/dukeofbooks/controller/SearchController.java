package seedu.dukeofbooks.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.inventory.InventoryDetails;

public class SearchController {
    public static final String BOOK_TITLE_NOT_FOUND = "Book title is not found";
    public static final String BOOK_TOPIC_NOT_FOUND = "Book topic is not found";

    public static Book searchBookByTitle(String titleString, Inventory inventory) throws IllegalValueException {
        HashMap<Book, InventoryDetails> listing = inventory.getInventoryMap();
        Optional<Book> target = listing.entrySet()
                                        .stream()
                                        .filter(e -> e.getKey().getTitle().equals(titleString))
                                        .map(Map.Entry::getKey)
                                        .findFirst();
        if (!target.isPresent()) {
            throw new IllegalValueException(BOOK_TITLE_NOT_FOUND);
        }
        return target.get();
    }
    public static Book searchBookByTopic(String topic, Inventory inventory) throws IllegalValueException {
        HashMap<Book, InventoryDetails> listing = inventory.getInventoryMap();
        Optional<Book> target = listing.entrySet()
                                        .stream()
                                        .filter(e -> e.getKey().getTopic().equals(topic))
                                        .map(Map.Entry::getKey)
                                        .findFirst();
        if (!target.isPresent()) {
            throw new IllegalValueException(BOOK_TOPIC_NOT_FOUND);
        }
        return target.get();
    }
}
