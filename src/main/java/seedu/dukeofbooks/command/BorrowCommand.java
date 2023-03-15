package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.book.BorrowableItem;

public class BorrowCommand extends Command {
    public static final String COMMAND_WORD = "borrow";
    Person person;
    BorrowableItem item;
    private String title;
    
    public BorrowCommand(Person person, BorrowableItem item) {
        this.person = person;
        this.item = item;
    }

    public BorrowCommand(String title){
        this.title=title;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Borrow is successful... Book is...");
    }
}
