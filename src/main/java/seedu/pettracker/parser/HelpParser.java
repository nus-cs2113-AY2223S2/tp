package seedu.pettracker.parser;

import seedu.pettracker.commands.HelpCommand;
import seedu.pettracker.exceptions.IllegalArgException;

public class HelpParser implements ArgParser<HelpCommand> {
    final String ARG_MESSAGE = "Help command should not have arguments";
    @Override
    public HelpCommand parse(String commandArgs) throws IllegalArgException {
        if (!commandArgs.isEmpty()) {
            throw new IllegalArgException(ARG_MESSAGE);
        }
        return new HelpCommand();
    }
}
