package seedu.pettracker.parser;

import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.commands.RemovePetCommand;

public class RemovePetParser implements ArgParser<RemovePetCommand>{
    @Override
    public RemovePetCommand parse(String commandArgs) throws EmptyPetNameException {
        if (commandArgs.isEmpty()) {
            throw new EmptyPetNameException();
        }
        return new RemovePetCommand(commandArgs);
    }
}
