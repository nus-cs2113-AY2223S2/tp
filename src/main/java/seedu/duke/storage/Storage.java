package seedu.duke.storage;

import seedu.duke.EventList;
import seedu.duke.NusModule;
import seedu.duke.Schedule;

import java.util.ArrayList;
import java.util.HashMap;

public interface Storage extends EventListStorage, NusModuleLoader{
    void saveToFile(EventList eventList);
    ArrayList<Schedule> loadEvents();
    HashMap<String, NusModule> loadModules();
}
