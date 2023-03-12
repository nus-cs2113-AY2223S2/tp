package seedu.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;

public class SearchControllerTest {
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
        searchController.searchBookByTitle("The only book");
    }

    public void searchBookByTopic_validBook_getBook() throws IllegalOperationException, IllegalValueException {
        searchController.setData(inventory);
        searchController.searchBookByTopic("python");
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
