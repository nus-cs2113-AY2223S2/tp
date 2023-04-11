package seedu.duke.command;

import seedu.duke.company.CompanyList;
import seedu.duke.event.Event;
import seedu.duke.venue.VenueList;

public class Command {

    protected String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType(){
        return commandType;
    }

    public void execute(CompanyList companyList) {}

    public void execute(VenueList venueList) {}

    public void execute(Event event, VenueList venueList) {}

    public void execute(Event event) {}

}

