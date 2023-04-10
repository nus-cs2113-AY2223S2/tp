package seedu.bigpp.command;

import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;

public abstract class Command {

    private String arguments;

    public abstract String executeCommand(DataStorage dataStorage) throws PPException;

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return arguments;
    }
}
