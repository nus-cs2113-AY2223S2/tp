package seedu.pettracker.parser;

import seedu.pettracker.commands.ListTasksCommand;
import seedu.pettracker.exceptions.IllegalArgException;

public class ListTasksParser implements ArgParser<ListTasksCommand> {
    final String ARG_MESSAGE = "list-tasks command should not have arguments";
    @Override
    public ListTasksCommand parse(String commandArgs) throws IllegalArgException {
        if (!commandArgs.isEmpty()) {
            throw new IllegalArgException(ARG_MESSAGE);
        }
        return new ListTasksCommand();
    }
}
