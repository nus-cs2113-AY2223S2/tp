package seedu.duke.venue;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyListException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class VenueListTest {
    @Test
    public void intDivision_zeroDivisor_exceptionThrown() {
        try {
            VenueList venueList = new VenueList(new ArrayList<>());
            venueList.printVenueInformation();
            fail();
        } catch (EmptyListException e) {
            assert true;
        }
    }
}
