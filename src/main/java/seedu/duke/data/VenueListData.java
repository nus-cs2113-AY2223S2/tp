package seedu.duke.data;

import seedu.duke.venue.Venue;

import java.util.ArrayList;

public class VenueListData {


    /**
     * Creates an array list of venues for the user
     *
     * @return a venueList which contains a list of venues
     */
    public static ArrayList<Venue> returnVenueList() {
        ArrayList<Venue> venues = new ArrayList<>();
        venues.add(new Venue(1,"Engineering Auditorium", "9 Engineering Drive 1 (S) 117576", 100));
        venues.add(new Venue(2, "Hon Sui Sen Auditorium", "1 Hon Sui Sen Drive (S) 117588", 100));
        venues.add(new Venue(3,"LT1", "10 Kent Ridge Crescent (S) 119260", 100));
        venues.add(new Venue(4,"LT6", "1 Hon Sui Sen Drive (S) 119260", 100));
        venues.add(new Venue(5,"University Cultural Centre", "50 Kent Ridge Crescent (S) 119279", 50));
        return venues;
    }
}
