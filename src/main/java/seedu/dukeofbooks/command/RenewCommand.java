package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;

public final class RenewCommand extends LoanCommand {
    public static final String COMMAND_WORD = "renew";
    private static final String SUCCESS_MSG = "Item has been renewed!";
    private static final String FAIL_MSG = "This book is not borrowed!";
    private static final String ERROR_MSG_F = "Cannot renew: %s";
    private final Person person;
    private final BorrowableItem item;
    
    public RenewCommand(LoanRecords loanRecords, Person person, BorrowableItem item) {
        super(loanRecords);
        this.person = person;
        this.item = item;
    }
    
    public RenewCommand(String title){
        this.title=title;
    }

    @Override
    public CommandResult execute() {
        if (!item.isBorrowed()) {
            return new CommandResult(FAIL_MSG);
        }

        try {
            LoanController.renewItem(loanRecords, person, item);
        } catch (LoanRecordNotFoundException e) {
            return new CommandResult(String.format(ERROR_MSG_F, e.getMessage()));
        }
        return new CommandResult(SUCCESS_MSG);
    }
}
    
