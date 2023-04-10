package seedu.pettracker.parser;

import seedu.pettracker.commands.AddPetCommand;
import seedu.pettracker.exceptions.EmptyPetNameException;

public class AddPetParser implements ArgParser<AddPetCommand> {
    @Override
    public AddPetCommand parse(String commandArgs) throws EmptyPetNameException {
        if (commandArgs.isEmpty()) {
            throw new EmptyPetNameException();
        }
        return new AddPetCommand(commandArgs);
    }
}
