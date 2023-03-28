package seedu.duke.command;

import seedu.duke.exception.EmptyListException;
import seedu.duke.venue.VenueList;

public class ListVenueCommand extends Command{
    public ListVenueCommand(String commandType){
        super(commandType);
    }

    /**
     * Prints out the list of available venues to the user
     *
     * @param venueList which contains a list of venues
     * @throws EmptyListException if error occurred due to empty list
     */
    @Override
    public void execute(VenueList venueList) {
        try {
            venueList.printVenueInformation();
        } catch (EmptyListException err){
            System.out.println("Nothing inside venue list");
        }
    }
}
