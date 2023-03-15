package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.book.BorrowHistory;
import seedu.dukeofbooks.data.person.Person;

public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    Person person;

    public HistoryCommand(Person person) {
        this.person = person;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(BorrowHistory.checkHistory(person));
    }
}

