package seedu.duke.data;

import seedu.duke.venue.Venue;

import java.util.ArrayList;

public class VenueListData {

    public static ArrayList<Venue> returnVenueList() {
        ArrayList<Venue> venues = new ArrayList<>();
        venues.add(new Venue("Engineering Auditorium", "9 Engineering Drive 1 (S) 117576", 100));
        venues.add(new Venue("Hon Sui Sen Auditorium", "1 Hon Sui Sen Drive (S) 117588", 100));
        venues.add(new Venue("LT1", "10 Kent Ridge Crescent (S) 119260", 100));
        venues.add(new Venue("LT6", "1 Hon Sui Sen Drive (S) 119260", 100));
        venues.add(new Venue("University Cultural Centre", "50 Kent Ridge Crescent (S) 119279", 50));
        return venues;
    }
}
