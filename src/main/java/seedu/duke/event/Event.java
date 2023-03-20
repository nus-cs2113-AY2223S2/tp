package seedu.duke.event;

import seedu.duke.company.CompanyList;
import seedu.duke.venue.Venue;
public class Event {

    private Venue venue;

    private CompanyList companyList;

    public Event(CompanyList companyList){
        this.companyList = companyList;
    }
}
