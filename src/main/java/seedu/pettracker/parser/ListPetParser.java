package seedu.pettracker.parser;

import seedu.pettracker.commands.ListPetCommand;
import seedu.pettracker.exceptions.IllegalArgException;

public class ListPetParser implements ArgParser<ListPetCommand>{
    @Override
    public ListPetCommand parse(String commandArgs) throws IllegalArgException {
        if (!commandArgs.isEmpty()) {
            throw new IllegalArgException("List command should not have arguments");
        }
        return new ListPetCommand();
    }
}
