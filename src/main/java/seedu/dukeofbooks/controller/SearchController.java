package seedu.dukeofbooks.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import seedu.dukeofbooks.common.Messages;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.inventory.InventoryDetails;
import static seedu.dukeofbooks.common.Messages.BOOK_TITLE_NOT_FOUND;
import static seedu.dukeofbooks.common.Messages.BOOK_TOPIC_NOT_FOUND;

public class SearchController {
    private static Inventory inventory;
    
    public static <T> void setData(T dataPoint) throws IllegalOperationException {
        if (!(dataPoint instanceof Inventory)) {
            throw new IllegalOperationException(Messages.DATA_NOT_VALID);
        }
        inventory = (Inventory) dataPoint;
    }

    public static boolean checkDataExists() throws IllegalOperationException {
        if (inventory == null) {
            throw new IllegalOperationException(Messages.DATA_NOT_SET);
        }
        return true;
    }

    public static Book searchBookByTitle(String titleString) throws IllegalValueException {
        assert(inventory != null);
        
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
    public static Book searchBookByTopic(String topic) throws IllegalValueException {
        assert(inventory != null);
        
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
