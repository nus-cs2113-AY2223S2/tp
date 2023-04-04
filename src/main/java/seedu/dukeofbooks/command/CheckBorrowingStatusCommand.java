package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;

public class CheckBorrowingStatusCommand extends UserCommand {
    public static final String COMMAND_WORD = "status";
    public static final String INCORRECT_SYNTAX = "INVALID SYNTAX\nExpected: status -title TITLE";
    private static final String NOT_FOUND_MSG = "Item not found!";
    private BorrowableItem item;

    public CheckBorrowingStatusCommand(BorrowableItem item) {
        this.item = item;
    }

    @Override
    public CommandResult execute() throws LoanRecordNotFoundException {
        if (item == null) {
            return new CommandResult(NOT_FOUND_MSG);
        }
        String message = LoanController.checkBorrowingStatus(item);
        return new CommandResult(message);
    }

}
