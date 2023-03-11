package seedu.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.inventory.Inventory;

class InventoryControllerTest {
    public Inventory inventory = new Inventory();
    public InventoryController inventoryController = new InventoryController();
    
    @Test
    public void setData_inventory_success() throws IllegalOperationException {
        inventoryController.setData(inventory);
        assertTrue(inventoryController.checkDataExists());
    }
    @Test
    public void addBook_validBook_addSuccess() throws IllegalOperationException {
        inventoryController.setData(inventory);
        inventoryController.addBook("1234567890123", "The only book", "writer", "python");
        assertTrue(true);
    }
    @Test
    public void removeBook_validBook_removeSuccess() throws IllegalOperationException {
        addBook_validBook_addSuccess();
        inventoryController.removeOneBook("1234567890123");
    }
    @Test
    public void removeBook_invalidBook_exception() throws IllegalOperationException {
        addBook_validBook_addSuccess();
        
        assertThrows(IllegalOperationException.class, 
                () -> inventoryController.removeOneBook("1234"));
    }
}