package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.IllegalOperationException;

public class InventoryCommand extends UserCommand {
    public static final String COMMAND_WORD = "librarian";
    public static final String ADD_WORD = "add";
    public static final String DELETE_WORD = "delete";
    public static final String INVALID_ACTION = "ERROR: Action only search by add or delete";
    public static final String INCORRECT_SYNTAX = "INVALID SYNTAX\nExpected: librarian -title"
            + " TITLE -topic TOPIC -author AUTHOR -isbn ISBN -action add";
    public static final String INVALID_ITEM = "ERROR: Item is not valid";
    public static final String ACTION_SUCCESS = "Action: %s is successful. %s updated";
    private String action;
    private BorrowableItem item;
    
    public InventoryCommand(BorrowableItem item, String action) {
        this.action = action;
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        try {
            Book targetBook = checkBook();
            assert(targetBook!=null);
            switch(action){
            case ADD_WORD:
                InventoryController.addBook(targetBook);
                break;
            case DELETE_WORD:
                InventoryController.removeAllBooks(targetBook.getIsbn().toString());
                break;
            default:
                throw new IllegalOperationException(INVALID_ACTION);
            }

            return new CommandResult(String.format(ACTION_SUCCESS,
                    action, targetBook.getTitle()));
        } catch (IllegalOperationException e) {
            return new CommandResult(e.getMessage());
        }
    }
    
    private Book checkBook() throws IllegalOperationException {
        if (!(this.item instanceof Book)) {
            throw new IllegalOperationException(INVALID_ITEM);
        }
        return (Book) item;
    }
}
