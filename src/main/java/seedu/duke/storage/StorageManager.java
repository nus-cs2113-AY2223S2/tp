package seedu.duke.storage;

import seedu.duke.EventList;
import seedu.duke.NusModule;
import seedu.duke.Schedule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages storage of EventList data and loading of NusMods data.
 */
public class StorageManager implements Storage{
    private EventListStorage eventListStorage;
    private NusModuleLoader nusModuleLoader;
    private boolean hasLoadMods = false;
    private HashMap<String, NusModule> nusMods;

    public StorageManager(EventListStorage eventListStorage, NusModuleLoader nusModuleLoader) {
        this.eventListStorage = eventListStorage;
        this.nusModuleLoader = nusModuleLoader;
    }

    @Override
    public void saveToFile(EventList eventList) {
        eventListStorage.saveToFile(eventList);
    }

    @Override
    public ArrayList<Schedule> loadEvents() {
        return eventListStorage.loadEvents();
    }

    @Override
    public HashMap<String, NusModule> loadModules() {
        if (!hasLoadMods){
            nusMods = nusModuleLoader.loadModules(); //only load once.
        }
        return nusMods;
    }

}
