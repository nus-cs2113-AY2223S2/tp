package seedu.duke.event;

import seedu.duke.company.CompanyList;
import seedu.duke.venue.Venue;
import seedu.duke.venue.VenueList;

public class Event {

    private Venue venue;

    private CompanyList companyList;

    public Event(CompanyList companyList){
        this.companyList = companyList;
    }

    public void updateVenue(VenueList venueList, int venueNum){
        venue = venueList.getVenue(venueNum);
    }

    @Override
    public String toString() {
        return venue.getVenueName();
    }


}
