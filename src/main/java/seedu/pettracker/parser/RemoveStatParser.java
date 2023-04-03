package seedu.pettracker.parser;

import seedu.pettracker.commands.RemoveStatCommand;
import seedu.pettracker.exceptions.EmptyArgException;

public class RemoveStatParser implements ArgParser<RemoveStatCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: PET_NAME STAT.";
    @Override
    public RemoveStatCommand parse(String commandArgs) throws EmptyArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            String[] args = commandArgs.split(" ");
            String petName = args[0];
            String statName = args[1];
            return new RemoveStatCommand(petName, statName);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgException(INVALID_ARG_FORMAT_MESSAGE);
        }
    }
}
