package seedu.duke.event;

import seedu.duke.company.CompanyList;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.ui.Ui;
import seedu.duke.venue.Venue;
import seedu.duke.venue.VenueList;

import java.util.NoSuchElementException;

public class Event {
    private Venue venue;

    private String eventName;

    private CompanyList companyList;

    private Ui ui;

    public Event(CompanyList companyList, Ui ui){
        this.companyList = companyList;
        this.ui = ui;
    }

    /**
     * Updates the venue chosen for the event
     *
     * @throws InvalidIndexException if error occurred due to invalid index
     */
    public void updateVenue(VenueList venueList, int venueNum) throws InvalidIndexException {
        if (venueNum < 1 | venueNum > venueList.getVenueListSize()) {
            throw new InvalidIndexException();
        }
        venue = venueList.getVenue(venueNum - 1);
        ui.showVenueSelectionMessage(venue.getVenueName());
    }

    public void updateEventName(String eventName){
        this.eventName = eventName;
        ui.showEventNameSelectionMessage(eventName);
    }

    public String getEventName() {
        return eventName;
    }
    public Venue getVenue() throws NoSuchElementException {
        if (venue == null){
            throw new NoSuchElementException();
        }
        return venue;
    }

    @Override
    public String toString() {
        return venue.getVenueName() + " is your chosen location!";
    }
}
