package seedu.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.inventory.Inventory;

class InventoryControllerTest {
    public Inventory inventory = new Inventory();
    
    @Test
    public void addBook_validBook_addSuccess() throws IllegalOperationException {
        InventoryController.setData(inventory);
        InventoryController.addBook("1234567890123", "The only book", "writer", "python");
        assertTrue(true);
    }
    @Test
    public void removeBook_validBook_removeSuccess() throws IllegalOperationException {
        InventoryController.setData(inventory);
        addBook_validBook_addSuccess();
        InventoryController.removeOneBook("1234567890123");
    }
    @Test
    public void removeBook_invalidBook_exception() throws IllegalOperationException {
        InventoryController.setData(inventory);
        addBook_validBook_addSuccess();
        
        assertThrows(IllegalOperationException.class, 
                () -> InventoryController.removeOneBook("1234"));
    }
}
