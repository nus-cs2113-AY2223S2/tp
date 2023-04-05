package seedu.duke.venue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VenueTest {
    @Test
    public void testCreatingVenue() {
        assertEquals("name1 loc1 7", new Venue(1,"name1","loc1", 7).toString());
        assertEquals("    0", new Venue(2, " "," ", 0).toString());
    }
}
