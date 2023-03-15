package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.book.BorrowableItem;
import seedu.dukeofbooks.data.person.Person;

public class RenewCommand extends Command {
    public static final String COMMAND_WORD = "renew";
    Person person;
    BorrowableItem item;
    private String title;
    
    public RenewCommand(Person person, BorrowableItem item) {
        this.person = person;
        this.item = item;
    }
    
    public RenewCommand(String title){
        this.title=title;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("book has been renewed! ");
    }
}
    
