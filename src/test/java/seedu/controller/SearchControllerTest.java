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
    public SearchController searchController = new SearchController();
    public InventoryController inventoryController = new InventoryController();

    @Test
    public void setData_inventory_success() throws IllegalOperationException {
        searchController.setData(inventory);
        assertTrue(searchController.checkDataExists());
    }

    public void searchBookByTitle_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        searchController.setData(inventory);
        Book target = searchController.searchBookByTitle(TITLE_TO_SEARCH);
        assertEquals(TITLE_TO_SEARCH, target.getTitle().toString());
    }

    public void searchBookByTopic_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        searchController.setData(inventory);
        Book target = searchController.searchBookByTopic(TOPIC_TO_SEARCH);
        assertEquals(TOPIC_TO_SEARCH, target.getTopic().toString());
    }

    @Test
    public void searchBook_validBook_success() throws IllegalOperationException, IllegalValueException {
        inventoryController.setData(inventory);
        inventoryController.addBook("1234567890123", "The only book", "writer", "python");
        System.out.println(inventory.getInventoryMap().size());
        searchBookByTitle_validBook_getBook();
        searchBookByTopic_validBook_getBook();
        assertTrue(true);
    }

    @Test
    public void searchBookByTitle_invalidBook_getException() throws IllegalValueException, IllegalOperationException {
        inventory = new Inventory();
        searchController.setData(inventory);

        assertThrows(IllegalValueException.class,
                () -> searchController.searchBookByTitle("The only book"));
    }
}
