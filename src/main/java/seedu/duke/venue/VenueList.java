package seedu.duke.venue;

import seedu.duke.exception.EmptyListException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class VenueList {

    private static ArrayList<Venue> venueList;

    private static Ui ui;

    public VenueList(ArrayList<Venue> venueList, Ui ui) {
        this.venueList = venueList;
        this.ui = ui;
    }

    /**
     * Prints the current venue list
     *
     * @throws EmptyListException if error occurred due to having an empty list
     */
    public static void printVenueInformation() throws EmptyListException {
        if (venueList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.showLine();
        for (int i = 0; i < venueList.size(); i++) {
            System.out.println(venueList.get(i));
        }
        ui.showLine();
    }

    public Venue getVenue(int venueNum) {
        return venueList.get(venueNum);
    }

    public int getVenueListSize(){
        return venueList.size();
    }
}
