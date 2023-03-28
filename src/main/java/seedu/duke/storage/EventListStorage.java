package seedu.duke.storage;

import seedu.duke.EventList;
import seedu.duke.Schedule;

import java.util.ArrayList;

public interface EventListStorage {
    void saveToFile(EventList eventList);

    ArrayList<Schedule> loadEvents();

}
