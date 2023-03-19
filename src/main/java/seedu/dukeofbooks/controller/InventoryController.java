package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.common.Messages;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;

public class InventoryController  {
    public static final String INVALID_BOOK_DATA = "Book values passed is not valid";
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

    public static Book addBook(String isbn, String title, String author, String topic)
                throws IllegalOperationException {
        assert(inventory!=null);
        try {
            Book newBook = new Book(isbn, title, topic, author);
            inventory.addBook(newBook);
            return newBook;
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }

    public static Book addBook(Book newBook) throws IllegalOperationException {
        assert(inventory!=null);
        inventory.addBook(newBook);
        return newBook;
    }

    public static Book removeAllBooks(String isbn) throws IllegalOperationException {
        assert(inventory!=null);

        try {
            return inventory.purgeBook(isbn);
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }

    public static Book removeAllBooks(Book book) throws IllegalOperationException {
        assert(inventory!=null);

        try {
            return inventory.purgeBook(book.getIsbn().toString());
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }

    public static Book removeOneBook(String isbn) throws IllegalOperationException {
        assert(inventory!=null);

        try {
            return inventory.removeBook(isbn);
        } catch (IllegalValueException e) {
            throw new IllegalOperationException(e.getMessage());
        }
    }
}
