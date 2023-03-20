package seedu.duke.venue;

import seedu.duke.exception.EmptyListException;

import java.util.ArrayList;

public class VenueList {

    private static ArrayList<Venue> venueList;

    public VenueList(ArrayList<Venue> venueList) {
        this.venueList = venueList;
    }
    
    public static void printVenueInformation() throws EmptyListException {
        if (venueList.isEmpty()) {
            throw new EmptyListException();
        }
        for (int i = 0; i < venueList.size(); i++) {
            System.out.println(venueList.get(i));
        }
    }

    public Venue getVenue(int venueNum) {
        return venueList.get(venueNum);
    }
}
