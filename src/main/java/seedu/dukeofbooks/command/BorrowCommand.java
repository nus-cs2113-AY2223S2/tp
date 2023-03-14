package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.book.BorrowableItem;

public class BorrowCommand extends Command {
    public static final String COMMAND_WORD = "history";
    Person person;
    BorrowableItem item;
    
    public BorrowCommand(Person person, BorrowableItem item) {
        this.person = person;
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Borrow is successful... Book is...");
    }
}
