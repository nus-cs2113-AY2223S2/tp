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
    public static final String VALID_BOOK_TITLE = "The only book";
    public static final String VALID_BOOK_ISBN = "1234567890123";
    public static final String VALID_BOOK_AUTHOR = "anon";
    public static final String VALID_BOOK_TOPIC = "python";
    public Inventory inventory = new Inventory();
    

    public void searchBookByTitle_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        Book target = SearchController.searchBookByTitle(VALID_BOOK_TITLE);
        assertEquals(VALID_BOOK_TITLE, target.getTitle().toString());
    }

    public void searchBookByTopic_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        Book target = SearchController.searchBookByTopic(VALID_BOOK_TOPIC);
        assertEquals(VALID_BOOK_TOPIC, target.getTopic().toString());
    }

    @Test
    public void searchBook_validBook_success() throws IllegalOperationException, IllegalValueException {
        InventoryController.setData(inventory);
        SearchController.setData(inventory);
        InventoryController.addBook(VALID_BOOK_ISBN, VALID_BOOK_TITLE, VALID_BOOK_AUTHOR, VALID_BOOK_TOPIC);
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
                () -> SearchController.searchBookByTitle(VALID_BOOK_TITLE));
    }
}
