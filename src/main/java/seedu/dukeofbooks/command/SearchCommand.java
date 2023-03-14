package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.book.BorrowableItem;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    BorrowableItem item;
    
    public SearchCommand(BorrowableItem item) {
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("book has been found");
    }
}
