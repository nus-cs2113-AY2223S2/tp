package seedu.pettracker.parser;

import seedu.pettracker.commands.Command;
import seedu.pettracker.exceptions.EmptyPetNameException;

public interface ArgParser<T extends Command> {
    /**
     * Parses commandArgs into a command and returns it.
     */
    T parse(String commandArgs) throws EmptyPetNameException;
}
