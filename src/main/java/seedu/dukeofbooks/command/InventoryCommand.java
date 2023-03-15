package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;

import static seedu.dukeofbooks.common.Messages.INVALID_CONTROLLER;

public class InventoryCommand extends Command {
    public static final String COMMAND_WORD = "librarian";
    public static final String ADD_WORD = "add";
    public static final String DELETE_WORD = "delete";
    public static final String INVALID_ACTION = "ERROR: Action only search by add or delete";
    public static final String INVALID_ITEM = "ERROR: Item is not valid";

    private Inventory inventory;
    private String action;
    private BorrowableItem item;
    
    public InventoryCommand(String action, BorrowableItem item) {
        this.action = action;
        this.item = item;
    }
    public InventoryCommand(String action, BorrowableItem item, Inventory inventory) {
        this(action,item);
        setData(inventory);
    }

    @Override
    public CommandResult execute() {
        try {
            Book targetBook = checkBook();

            switch(action){
            case ADD_WORD:
                inventory.addBook(targetBook);
                break;
            case DELETE_WORD:
                inventory.purgeBook(targetBook.getIsbn().toString());
                break;
            default:
                throw new IllegalOperationException(INVALID_ACTION);
            }

            return new CommandResult(String.format("Action: %s is successful. %s updated",
                    action, targetBook.getTitle()));
        } catch (IllegalOperationException | IllegalValueException e) {
            return new CommandResult(e.getMessage());
        }
    }
    
    public void setData(Inventory inventory) {
        this.inventory = inventory;
    }
    private InventoryController checkController() throws IllegalOperationException {
        // check if controller of correct type
        if (!(this.controller instanceof InventoryController)) {
            throw new IllegalOperationException(INVALID_CONTROLLER);
        }
        return (InventoryController) controller;
    }
    private Book checkBook() throws IllegalOperationException {
        if (!(this.item instanceof Book)) {
            throw new IllegalOperationException(INVALID_ITEM);
        }
        return (Book) item;
    }
}
