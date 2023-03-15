package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import java.util.ArrayList;

// import static org.junit.jupiter.api.Assertions.assertEquals;


class EventListTest {

    public final EventList eventList = new EventList();

    @BeforeEach
    void setup() throws NPExceptions{
        eventList.addEvent("test", "20:00", "2023/03/14");
    }
    @Test
    void addEvent() {
        assert (eventList.getSize() == 1);
    }
    @Test
    void getDetails() {
        String expected = "[E] test (2023/03/14 20:00)";
        String ouput = eventList.getDetails(0);
        assert(eventList.getDetails(0).equals(expected));
    }

    @Test
    void deleteThisTask() {
        eventList.deleteThisTask(0);
        assert(eventList.getSize() == 0);
    }

    @Test
    void testEditCommand() throws NPExceptions {
        eventList.addEvent("test", "20:00", "2023/03/14");
        eventList.reviseTimeInfo(0, "21:00", "2023/03/14", "23:00", "2023/03/14");
    }
}
