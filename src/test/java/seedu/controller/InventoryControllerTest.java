package seedu.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;

class InventoryControllerTest {
    public static final String INVALID_ISBN = "123";
    public static final String VALID_ISBN = "1234567890123";
    public static final String VALID_TITLE = "The only book";
    public static final String VALID_AUTHOR = "writer";
    public static final String VALID_TOPIC = "python";
    public Inventory inventory = new Inventory();
    
    @Test
    @BeforeAll
    public static void noInventoryObject_checkFails() {
        assertThrows(IllegalOperationException.class,
                () -> InventoryController.checkDataExists());
    }

    @Test
    public void addRandomObject_addSuccess() throws IllegalOperationException {
        assertThrows(IllegalOperationException.class,
                () -> InventoryController.setData(VALID_AUTHOR));
    }
    
    @Test
    public void addInventoryObject_addSuccess() throws IllegalOperationException {
        InventoryController.setData(inventory);
        assertTrue(InventoryController.checkDataExists());
    }

    
    @Test
    public void addBook_validBook_addSuccess() throws IllegalOperationException {
        InventoryController.setData(inventory);
        InventoryController.addBook(VALID_ISBN, VALID_TITLE, VALID_AUTHOR, VALID_TOPIC);
        assertTrue(true);
    }

    @Test
    public void addBookObject_validBook_addSuccess() throws IllegalOperationException, IllegalValueException {
        InventoryController.setData(inventory);
        Book newBook = new Book(VALID_ISBN, VALID_TITLE, VALID_TOPIC, VALID_AUTHOR);
        InventoryController.addBook(newBook);
        assertTrue(true);
    }
    @Test
    public void removeBook_validBook_removeSuccess() throws IllegalOperationException {
        InventoryController.setData(inventory);
        addBook_validBook_addSuccess();
        InventoryController.removeOneBook(VALID_ISBN);
    }
    @Test
    public void removeBookByObject_validBook_removeSuccess() throws IllegalOperationException, IllegalValueException {
        InventoryController.setData(inventory);
        Book newBook = new Book(VALID_ISBN, VALID_TITLE, VALID_TOPIC, VALID_AUTHOR);
        InventoryController.addBook(newBook);
        InventoryController.removeAllBooks(newBook);
    }
    @Test
    public void removeBook_invalidBook_exception() throws IllegalOperationException {
        InventoryController.setData(inventory);
        addBook_validBook_addSuccess();
        
        assertThrows(IllegalOperationException.class, 
                () -> InventoryController.removeOneBook(INVALID_ISBN));
    }
}
