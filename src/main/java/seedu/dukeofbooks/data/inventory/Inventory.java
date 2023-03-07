package seedu.dukeofbooks.data.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalValueException;

public class Inventory {
    public static final String INVALID_BOOK_REMOVAL = "Book being removed does not exist";
    private HashMap<Book, InventoryDetails> inventoryListing;

    public Inventory() {
        inventoryListing = new HashMap<Book, InventoryDetails>();
    }
    public boolean bookExists(Book book) {
        Optional<InventoryDetails> target = inventoryListing.entrySet()
                                                    .stream()
                                                    .filter(e -> e.getKey().equals(book))
                                                    .map(Map.Entry::getValue)
                                                    .findFirst();
        return target.isPresent();
    }
    public HashMap<Book, InventoryDetails> getInventoryMap(){
        return inventoryListing;
    }
    public void addBook(Book book) {
        // Check if book in inventory
        if (inventoryListing.get(book) == null) {
            // Create book and exit
            createBookEntry(book);
            return;
        }
        incrementBookEntry(book);
    }

    /**
     * Removes one book from inventory
     * 
     * @param book
     * @throws IllegalValueException
     */
    public void removeBook(Book book) throws IllegalValueException {
        // Check if book in inventory
        if (inventoryListing.get(book) == null) {
            // Create book and exit
            throw new IllegalValueException(INVALID_BOOK_REMOVAL);
        }
        decrementBookEntry(book);
    }

    /**
     * Removes all books from inventory
     * 
     * @param book
     * @throws IllegalValueException
     */
    public void purgeBook(Book book) throws IllegalValueException {
        // Check if book in inventory
        if (inventoryListing.get(book) == null) {
            // Create book and exit
            throw new IllegalValueException(INVALID_BOOK_REMOVAL);
        }
        inventoryListing.remove(book);
    }

    private void createBookEntry(Book book) {
        InventoryDetails details = new InventoryDetails();
        inventoryListing.put(book, details);
    }

    private void incrementBookEntry(Book book) {
        inventoryListing.get(book).incrementCount();
    }

    private void decrementBookEntry(Book book) throws IllegalValueException {
        inventoryListing.get(book).decrementCount();
    }
}
