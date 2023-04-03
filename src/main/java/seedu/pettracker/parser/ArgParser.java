package seedu.pettracker.parser;

import seedu.pettracker.exceptions.IllegalArgException;

public interface ArgParser<Command> {
    /**
     * Parses commandArgs into a command and returns it.
     */
    Command parse(String commandArgs) throws IllegalArgException;
}
