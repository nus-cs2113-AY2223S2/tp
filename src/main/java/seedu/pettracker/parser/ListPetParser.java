package seedu.pettracker.parser;

import seedu.pettracker.commands.ListPetCommand;
import seedu.pettracker.exceptions.IllegalArgException;

public class ListPetParser implements ArgParser<ListPetCommand>{
    final String ARG_MESSAGE = "List command should not have arguments";
    @Override
    public ListPetCommand parse(String commandArgs) throws IllegalArgException {
        if (!commandArgs.isEmpty()) {
            throw new IllegalArgException(ARG_MESSAGE);
        }
        return new ListPetCommand();
    }
}
