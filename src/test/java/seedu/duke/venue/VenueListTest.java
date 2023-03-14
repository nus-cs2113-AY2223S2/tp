package seedu.duke.venue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.EmptyListException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VenueListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private ArrayList<Venue> venueArrayList = new ArrayList<>();
    private VenueList venueList = new VenueList(venueArrayList);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void printVenueListTest() throws EmptyListException {
        venueList.print();
        assertEquals("Nothing inside venue list", outputStreamCaptor.toString()
                .trim());
    }
}
