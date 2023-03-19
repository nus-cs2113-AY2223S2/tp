package seedu.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.book.Book;

public class SearchControllerTest {
    public static final String TITLE_TO_SEARCH = "The only book";
    public static final String TOPIC_TO_SEARCH = "python";
    public Inventory inventory = new Inventory();
    

    public void searchBookByTitle_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        Book target = SearchController.searchBookByTitle(TITLE_TO_SEARCH);
        assertEquals(TITLE_TO_SEARCH, target.getTitle().toString());
    }

    public void searchBookByTopic_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        Book target = SearchController.searchBookByTopic(TOPIC_TO_SEARCH);
        assertEquals(TOPIC_TO_SEARCH, target.getTopic().toString());
    }

    @Test
    public void searchBook_validBook_success() throws IllegalOperationException, IllegalValueException {
        InventoryController.setData(inventory);
        SearchController.setData(inventory);
        InventoryController.addBook("1234567890123", "The only book", "writer", "python");
        System.out.println(inventory.getInventoryMap().size());
        searchBookByTitle_validBook_getBook();
        searchBookByTopic_validBook_getBook();
        assertTrue(true);
    }

    @Test
    public void searchBookByTitle_invalidBook_getException() throws IllegalValueException, IllegalOperationException {
        SearchController.setData(inventory);
        InventoryController.setData(inventory);
        assertThrows(IllegalValueException.class,
                () -> SearchController.searchBookByTitle("The only book"));
    }
}
