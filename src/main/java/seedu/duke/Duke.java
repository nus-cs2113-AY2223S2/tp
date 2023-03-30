package seedu.duke;

import seedu.duke.storage.EventListStorage;
import seedu.duke.storage.NusModuleLoader;
import seedu.duke.storage.JsonEventListStorage;
import seedu.duke.storage.JsonNusModuleLoader;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final EventList eventTracker;
    private final Ui ui;
    private final Parser parser;
    private final EventListStorage eventListStorage;
    private final NusModuleLoader nusModuleLoader;
    private final Storage storage;

    public Duke (){
        startLogger();
        ui = new Ui();
        eventListStorage = new JsonEventListStorage();
        nusModuleLoader = new JsonNusModuleLoader();
        storage = new StorageManager(eventListStorage, nusModuleLoader);
        eventTracker = new EventList(storage.loadEvents());
        parser = new Parser();
    }

    public void run(){
        LOGGER.log(Level.INFO, "NUSPlanner is starting!");
        ui.showWelcome();
        ui.getSemester();
        LOGGER.log(Level.INFO, "User has successfully input semester they are in.");
        ui.getUserCommand(eventTracker);
        LOGGER.log(Level.INFO, "User input is 'bye', proceeding to save edits to file (if any).");
        LOGGER.log(Level.INFO, "Exiting NUSPlanner.");
        storage.saveToFile(eventTracker); //Will only save to file on exit
    }

    public static void main(String[] args) {
        Duke nusPlanner = new Duke();
        nusPlanner.run();
    }

    /**
     * Starts logger to log information throughout the execution
     * of NUSPlanner. The logging information written to "log.txt"
     */
    private static void startLogger() {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleH = new ConsoleHandler();
        consoleH.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleH);

        try {
            FileHandler fileH = new FileHandler("log.txt");
            fileH.setLevel(Level.FINE);
            SimpleFormatter formatter = new SimpleFormatter();
            fileH.setFormatter(formatter);
            LOGGER.addHandler(fileH);
        } catch (java.io.IOException e) {
            LOGGER.log(Level.SEVERE, "Error detected in file logger!", e);
        }
    }
}
