package seedu.duke;

// import java.util.Scanner;
//import seedu.duke.ui.Ui;
//import seedu.duke.EventList;
//import seedu.duke.parser.Parser;


import seedu.duke.storage.EventListStorage;
import seedu.duke.storage.NusModuleLoader;
import seedu.duke.storage.JsonEventListStorage;
import seedu.duke.storage.JsonNusModuleLoader;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final EventList eventTracker;
    private final Ui ui;
    private final Parser parser;
    private final EventListStorage eventListStorage;
    private final NusModuleLoader nusModuleLoader;
    private final Storage storage;
    public Duke (){
        ui = new Ui();
        eventListStorage = new JsonEventListStorage();
        nusModuleLoader = new JsonNusModuleLoader();
        storage = new StorageManager(eventListStorage, nusModuleLoader);
        eventTracker = new EventList(storage.loadEvents());
        parser = new Parser();
    }
    public void run(){
        ui.showWelcome();
        ui.getSemester();
        ui.getUserCommand(eventTracker);
        storage.saveToFile(eventTracker); //Will only save to file on exit
    }

    public static void main(String[] args) {
        Duke nusPlanner = new Duke();
        nusPlanner.run();
    }
}
