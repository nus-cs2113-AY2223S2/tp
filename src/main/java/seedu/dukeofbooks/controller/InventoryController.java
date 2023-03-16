package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.common.Messages;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;

public class InventoryController implements IController {
    public static final String INVALID_BOOK_DATA = "Book values passed is not valid";
    private Inventory inventory;

    public InventoryController() {
        inventory = null;
    }

    @Override
    public <T> void setData(T dataPoint) throws IllegalOperationException {
        if (!(dataPoint instanceof Inventory)) {
            throw new IllegalOperationException(Messages.DATA_NOT_VALID);
        }
        inventory = (Inventory) dataPoint;
    }

    public Book addBook(String isbn, String title, String author, String topic) throws IllegalOperationException {
        try {
            checkDataExists();
            Book newBook = new Book(isbn, title, topic, author);
            inventory.addBook(newBook);
            return newBook;
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }
    public Book addBook(Book newBook) throws IllegalOperationException {
        checkDataExists();
        inventory.addBook(newBook);
        return newBook;
    }

    public Book removeAllBooks(String isbn) throws IllegalOperationException {
        checkDataExists();

        try {
            return inventory.purgeBook(isbn);
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }

    public Book removeAllBooks(Book book) throws IllegalOperationException {
        checkDataExists();

        try {
            return inventory.purgeBook(book.getIsbn().toString());
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }

    public Book removeOneBook(String isbn) throws IllegalOperationException {
        checkDataExists();

        try {
            return inventory.removeBook(isbn);
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }

    @Override
    public boolean checkDataExists() throws IllegalOperationException {
        if (inventory == null) {
            throw new IllegalOperationException(Messages.DATA_NOT_SET);
        }
        return true;
    }
}
