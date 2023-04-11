package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.storage.EventDetailsStorage;

import java.util.NoSuchElementException;

public class UpdateEventNameCommand extends Command {

    protected String newEventName;

    public UpdateEventNameCommand(String commandType, String newEventName) {
        super(commandType);
        this.newEventName = newEventName;
    }

    @Override
    public void execute(Event event) {
        try {
            event.updateEventName(newEventName);
            int venueIndex = event.getVenue().getVenueIndex();
            EventDetailsStorage.updateFile(event, venueIndex);
        } catch (NoSuchElementException err){
            System.out.println("Note: No venue has been chosen yet!");
        } finally {
            EventDetailsStorage.updateFile(event);
        }
    }
}
