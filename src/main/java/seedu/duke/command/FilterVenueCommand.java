package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.venue.Venue;
import seedu.duke.venue.VenueList;

public class FilterVenueCommand extends Command {

    protected int venueSize;

    Ui ui = new Ui();

    public FilterVenueCommand(String commandType, int venueSize) {
        super(commandType);
        this.venueSize = venueSize;
    }

    @Override
    public void execute(VenueList venueList) {
        ui.showLine();
        int numberOfSuitableVenues = 0;
        for (int i = 0; i < venueList.getVenueListSize(); i += 1) {
            Venue currVenue = venueList.getVenue(i);
            if (currVenue.getVenueCapacity() >= venueSize) {
                System.out.println(currVenue.toString());
                numberOfSuitableVenues += 1;
            }
        }
       if (numberOfSuitableVenues == 0) {
           System.out.println("No suitable venues found.");
       }
       ui.showLine();
    }
}
