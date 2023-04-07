package seedu.pettracker.parser;

import seedu.pettracker.commands.ExitCommand;
import seedu.pettracker.exceptions.IllegalArgException;

public class ExitParser implements ArgParser<ExitCommand> {
    final String ARG_MESSAGE = "Exit command should not have arguments";
    @Override
    public ExitCommand parse(String commandArgs) throws IllegalArgException {
        if (!commandArgs.isEmpty()) {
            throw new IllegalArgException(ARG_MESSAGE);
        }
        return new ExitCommand();
    }
}
