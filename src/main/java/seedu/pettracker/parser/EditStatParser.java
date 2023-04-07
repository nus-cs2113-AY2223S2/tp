package seedu.pettracker.parser;

import seedu.pettracker.commands.EditStatCommand;
import seedu.pettracker.exceptions.EmptyArgException;

public class EditStatParser implements ArgParser<EditStatCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: PETNAME STAT.";
    @Override
    public EditStatCommand parse(String commandArgs) throws EmptyArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            String[] args = commandArgs.split(" ");
            String petName = args[0];
            String statName = args[1];
            String newStatName = args[2];
            return new EditStatCommand(petName, statName, newStatName);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgException(INVALID_ARG_FORMAT_MESSAGE);
        }
    }
}
