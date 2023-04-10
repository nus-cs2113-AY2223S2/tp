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

    /**
     * Constructor for the Storage Manager Class
     * @param eventListStorage EventListSTorage is responsible for loading/saving the event List from/to save.json
     * @param nusModuleLoader nusModuleLoader is responsible for loading module information from nusmods.json
     */
    public StorageManager(EventListStorage eventListStorage, NusModuleLoader nusModuleLoader) {
        this.eventListStorage = eventListStorage;
        this.nusModuleLoader = nusModuleLoader;
    }

    /**
     * Writes EventList to save.json
     * @param eventList the EventList object to be saved in JSON format.
     */
    @Override
    public void saveToFile(EventList eventList) {
        eventListStorage.saveToFile(eventList);
    }

    /**
     * Loads Events from save.json as a array list
     * @return ArrayList consisting of saved events.
     */
    @Override
    public ArrayList<Schedule> loadEvents() {
        return eventListStorage.loadEvents();
    }

    /**
     * Loads Modules from nusmods.json
     * @return HashMap containing all modules offered by NUS in AY22/23. Accurate as of March 17, 2023.
     */
    @Override
    public HashMap<String, NusModule> loadModules() {
        if (!hasLoadMods){
            nusMods = nusModuleLoader.loadModules(); //only load once.
        }
        return nusMods;
    }

}
