package seedu.duke.venue;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyListException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class VenueListTest {
    @Test
    public void testPrintVenueList() {
        try {
            VenueList venueList = new VenueList(new ArrayList<>());
            venueList.printVenueInformation();
            fail();
        } catch (EmptyListException err) {
            assert true;
        }
    }
}
