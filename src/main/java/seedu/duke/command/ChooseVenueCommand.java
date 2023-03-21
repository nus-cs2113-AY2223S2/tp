package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.venue.VenueList;

public class ChooseVenueCommand extends Command{

    protected int venueNum;

    public ChooseVenueCommand(String commandType, int venueNum) {
        super(commandType);
        this.venueNum = venueNum;
    }

    @Override
    public void execute(Event event, VenueList venueList) {
        try{
            event.updateVenue(venueList, venueNum);
        } catch (InvalidIndexException err){
            System.out.println("Invalid index provided! Please try again");
        }
    }
}
