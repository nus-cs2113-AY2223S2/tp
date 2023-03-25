package seedu.duke.event;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.ui.Ui;
import seedu.duke.venue.Venue;
import seedu.duke.venue.VenueList;

public class Event {

    private Venue venue;

    private CompanyList companyList;

    private Ui ui;

    public Event(CompanyList companyList, Ui ui){
        this.companyList = companyList;
        this.ui = ui;
    }

    public void updateVenue(VenueList venueList, int venueNum) throws InvalidIndexException {
        if (venueNum < 0 | venueNum >= venueList.getVenueListSize()) {
            throw new InvalidIndexException();
        }
        venue = venueList.getVenue(venueNum);
        ui.showVenueSelectionMessage(venue.getVenueName());
    }

    @Override
    public String toString() {
        return venue.getVenueName() + " is your chosen location!";
    }
}
