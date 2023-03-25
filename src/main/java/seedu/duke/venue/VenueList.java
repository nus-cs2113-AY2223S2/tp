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
    
    public static void printVenueInformation() throws EmptyListException {
        if (venueList.isEmpty()) {
            throw new EmptyListException();
        }
        for (int i = 0; i < venueList.size(); i++) {
            System.out.println(i + 1 + ". " + venueList.get(i));
        }
    }

    public Venue getVenue(int venueNum) {
        return venueList.get(venueNum);
    }

    public int getVenueListSize(){
        return venueList.size();
    }
}
