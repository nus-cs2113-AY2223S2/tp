package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.book.BorrowableItem;

public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";
    Person person;
    BorrowableItem item;
    
    public ReturnCommand(Person person, BorrowableItem item) {
        this.person = person;
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        // update the loan records

        return new CommandResult("some string");
    }
}
