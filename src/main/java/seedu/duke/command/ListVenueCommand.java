package seedu.duke.command;

import seedu.duke.exception.EmptyListException;
import seedu.duke.venue.VenueList;

public class ListVenueCommand extends Command{
    public ListVenueCommand(String commandType){
        super(commandType);
    }
    @Override
    public void execute(VenueList venueList) {
        try {
            venueList.print();
        } catch (EmptyListException err){
            System.out.println("Nothing inside venue list");
        }
    }
}
