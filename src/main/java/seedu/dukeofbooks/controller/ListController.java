package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.common.Messages;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.inventory.InventoryDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class ListController {
    private static Inventory inventory;
    
    public static <T> void setData(T dataPoint) throws IllegalOperationException {
        if (!(dataPoint instanceof Inventory)) {
            throw new IllegalOperationException(Messages.DATA_NOT_VALID);
        }
        inventory = (Inventory) dataPoint;
    }

    public static ArrayList<Book> listBooks() throws IllegalValueException {
        assert(inventory != null);

        // create an ArrayList to store the books
        ArrayList<Book> bookList = new ArrayList<>();

        try {
            HashMap<Book, InventoryDetails> listing = inventory.getInventoryMap();
            // iterate over the keys (i.e., books) in the HashMap and add them to the ArrayList
            bookList.addAll(listing.keySet());
        } catch (Exception e){
            return bookList;
        }

        return bookList;
    }
}
