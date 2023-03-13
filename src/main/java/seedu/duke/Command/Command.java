package seedu.duke.Command;

import seedu.duke.VenueList;

public abstract class Command {

    protected String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }
    public abstract void execute(VenueList venueList);
}

