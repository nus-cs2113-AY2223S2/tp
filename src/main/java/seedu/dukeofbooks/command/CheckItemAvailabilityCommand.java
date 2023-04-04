package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.book.BorrowableItem;

public class CheckItemAvailabilityCommand extends UserCommand {
    public static final String COMMAND_WORD = "check";
    public static final String INCORRECT_SYNTAX = "INVALID SYNTAX\nExpected: check -title TITLE";
    private static final String NOT_FOUND_MSG = "Item not found!";

    private BorrowableItem item;

    public CheckItemAvailabilityCommand(BorrowableItem item) {
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        if (item == null) {
            return new CommandResult(NOT_FOUND_MSG);
        }
        String message = LoanController.checkItemAvailability(item);
        return new CommandResult(message);
    }

}
