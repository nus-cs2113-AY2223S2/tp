package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.EventDetailsStorage;
import seedu.duke.ui.Ui;
import seedu.duke.venue.VenueList;

public class ChooseVenueCommand extends Command{

    protected int venueNum;

    Ui ui = new Ui();

    public ChooseVenueCommand(String commandType, int venueNum) {
        super(commandType);
        this.venueNum = venueNum;
    }

    /**
     * Updates the venue chosen for the event
     *
     * @param event which contains an event
     * @param venueList which contains a list of venues
     * @throws InvalidIndexException if error occurred due to invalid index
     */
    @Override
    public void execute(Event event, VenueList venueList) {
        try{
            event.updateVenue(venueList, venueNum);
            EventDetailsStorage.updateFile(event, venueNum);
        } catch (InvalidIndexException err){
            ui.showLine();
            System.out.println("Invalid index provided! Please try again");
            ui.showLine();
        }
    }
}
