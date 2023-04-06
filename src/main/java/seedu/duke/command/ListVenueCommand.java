package seedu.duke.command;

import seedu.duke.exception.EmptyListException;
import seedu.duke.ui.Ui;
import seedu.duke.venue.VenueList;

public class ListVenueCommand extends Command{
    Ui ui = new Ui();
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
            ui.showLine();
            System.out.println("Nothing inside venue list");
            ui.showLine();
        }
    }
}
