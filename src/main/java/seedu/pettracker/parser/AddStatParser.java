package seedu.pettracker.parser;

import seedu.pettracker.commands.AddStatCommand;
import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.EmptyStatException;

public class AddStatParser implements ArgParser<AddStatCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: PETNAME STAT VALUE.";

    @Override
    public AddStatCommand parse(String commandArgs) throws EmptyStatException, EmptyArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            String[] args = commandArgs.split(" ");
            String petName = args[0];
            String stat = args[1];
            String value = args[2];
            return new AddStatCommand(petName, stat, value);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyStatException(INVALID_ARG_FORMAT_MESSAGE);
        }

    }
}
