package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.exception.DuplicateActionException;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.book.BorrowableItem;

import java.time.LocalDateTime;

public final class BorrowCommand extends LoanCommand {
    public static final String COMMAND_WORD = "history";
    private final Person person;
    private final BorrowableItem item;

    public BorrowCommand(LoanRecords loanRecords,Person person, BorrowableItem item) {
        super(loanRecords);
        this.person = person;
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        if (item.isBorrowed()) {
            return new CommandResult("This item is not borrowable.");
        }
        LocalDateTime borrowTime = LocalDateTime.now();
        try {
            LoanController.borrowItem(loanRecords, person, item, borrowTime);
        } catch (DuplicateActionException dae) {
            return new CommandResult("Cannot borrow: " + dae.getMessage());
        }
        return new CommandResult("Borrow is successful.");
    }
}
