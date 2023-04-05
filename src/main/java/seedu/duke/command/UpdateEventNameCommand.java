package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.storage.EventDetailsStorage;
import seedu.duke.venue.VenueList;

public class UpdateEventNameCommand extends Command {

    protected String newEventName;

    public UpdateEventNameCommand(String commandType, String newEventName) {
        super(commandType);
        this.newEventName = newEventName;
    }

    @Override
    public void execute(Event event, VenueList venueList) {
        event.updateEventName(newEventName);
        EventDetailsStorage.updateFile(event);
    }
}
