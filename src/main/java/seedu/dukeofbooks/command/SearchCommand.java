package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;

import static seedu.dukeofbooks.common.Messages.INVALID_CONTROLLER;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String INVALID_ITEM_BORROWED = "ERROR: Item is not valid";
    public static final String INVALID_ACTION = "ERROR: Action only search by title or topic";
    public static final String TITLE_SEARCH = "title";
    public static final String TOPIC_SEARCH = "topic";

    BorrowableItem item;
    String action;
    Inventory inventory;
    
    public SearchCommand(BorrowableItem item, String action) {
        this.item = item;
        this.action = action;
    }

    public SearchCommand(BorrowableItem item, String action, Inventory inventory) {
        this(item,action);
        this.inventory = inventory;

    }

    @Override
    public CommandResult execute() {
        try {
            SearchController searchController = getSearchController();

            Book targetBook;
            switch(action){
            case TITLE_SEARCH:
                targetBook= searchController.searchBookByTitle(getTitle(item));
                break;
            case TOPIC_SEARCH:
                targetBook=searchController.searchBookByTopic(getTopic(item));
                break;
            default:
                throw new IllegalOperationException(INVALID_ACTION);
            }

            return new CommandResult(String.format("Results: %s",targetBook.toString()));

        } catch (IllegalOperationException | IllegalValueException e) {
            return new CommandResult(e.getMessage());
        }
    }
    private SearchController getSearchController() throws IllegalOperationException {
        if (controller != null) {
            SearchController searchController = checkController();
            searchController.checkDataExists();
            return searchController;
        }

        SearchController searchController = new SearchController();
        searchController.setData(inventory);
        return searchController;
    }
    private SearchController checkController() throws IllegalOperationException {
        // check if controller of correct type
        if (!(this.controller instanceof SearchController)) {
            throw new IllegalOperationException(INVALID_CONTROLLER);
        }
        return (SearchController) controller;
    }
    private String getTitle(BorrowableItem item) throws IllegalOperationException {
        if (!(item instanceof Book)) {
            throw new IllegalOperationException(INVALID_ITEM_BORROWED);
        }
        Book bookItem = (Book) item;
        return bookItem.getTitle().toString();
    }
    private String getTopic(BorrowableItem item) throws IllegalOperationException {
        if (!(item instanceof Book)) {
            throw new IllegalOperationException(INVALID_ITEM_BORROWED);
        }
        Book bookItem = (Book) item;
        return bookItem.getTopic().toString();
    }
}
