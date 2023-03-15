package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.book.BorrowableItem;

public class ReturnCommand extends Command {
    public static final String COMMAND_WORD = "return";
    Person person;
    BorrowableItem item;
    private String title;
    
    public ReturnCommand(Person person, BorrowableItem item) {
        this.person = person;
        this.item = item;
    }

    public ReturnCommand(String title){
        this.title=title;
    }

    @Override
    public CommandResult execute() {
        // update the loan records

        return new CommandResult("some string");
    }
}
