package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import seedu.duke.exceptions.DukeError;
import seedu.duke.userdata.UserCareerData;

import java.io.File;
import java.time.LocalDateTime;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class StorageHandler {
    private static Logger logger = Logger.getLogger("Storage");
    private final UserDataWriter userDataWriter;
    private final UserDataLoader userDataLoader;
    private final String filePath;
    private Gson gson;
    private GsonBuilder gsonBuilder;

    public StorageHandler (String filePath) {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        this.userDataWriter = new UserDataWriter(gson);
        this.userDataLoader = new UserDataLoader(gson);
        this.filePath = filePath;
        initLogger();
    }

    public void initLogger () {
        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        try {
            File logFile = new File("logging.xml");
            if (!logFile.exists()) {
                new File("logging.xml").createNewFile();
            }
            FileHandler fileHandler = new FileHandler("logging.xml", true);
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to create a new logfile");
        }
    }

    public void writeToJson (UserCareerData userCareerData) throws DukeError {
        boolean writeStatus = this.userDataWriter.saveToJson(filePath, userCareerData);
        assert writeStatus : "An exception should be thrown, this part of code should not be run";
        logger.log(Level.INFO, "User Data has been written to file");

    }

    public UserCareerData loadUserCareer () {
        UserCareerData userCareerData;
        try {
            userCareerData = userDataLoader.loadFromJson(filePath);
            logger.log(Level.INFO, "Data has been restored from previous session!");
        } catch (DukeError e) {
            userCareerData = new UserCareerData();
            try {
                writeToJson(userCareerData);
                logger.log(Level.WARNING,
                           "Data file has been corrupted or missing, we will create a new file and reset " +
                                   "your progress.");
            } catch (DukeError error) {
                System.out.println(error.getMessage());
                logger.log(Level.SEVERE, "Unable to write new user data file to hard disk!");
            }
        }
        return userCareerData;
    }

}
