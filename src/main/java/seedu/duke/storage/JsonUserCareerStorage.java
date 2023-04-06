package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.duke.commons.LogMaster;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.UserCareerData;
//@@author EangJS

/**
 * This class is responsible to save all user career into a json file for storage on the hard disk.
 */
public class JsonUserCareerStorage implements UserCareerStorage {
    private static final Logger logger = LogMaster.getLogger(JsonUserCareerStorage.class);
    private final String userFilePath;
    private final Gson gson;
    private final JsonUserDataLoader jsonUserDataLoader;
    private final JsonUserDataWriter jsonUserDataWriter;

    /**
     * Constructor for the Storage handler of user career storage to convert into json format using the gson library.
     *
     * @param userFilePath Desired file path in which user career data is saved.
     */
    public JsonUserCareerStorage (String userFilePath) {
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();
        this.jsonUserDataWriter = new JsonUserDataWriter(gson);
        this.jsonUserDataLoader = new JsonUserDataLoader(gson);
        this.userFilePath = userFilePath;
    }

    /**
     * Writes the given user career data into json file which is saved on the hard disk.
     *
     * @param userCareerData The user career data that is desired to be saved to the hard disk.
     * @throws DukeError Occurs when there is a file write error.
     */
    @Override
    public void writeToJson (UserCareerData userCareerData) throws DukeError {
        boolean isSaved = jsonUserDataWriter.saveToJson(userFilePath, userCareerData);
        assert isSaved : "An exception should be thrown, this part of code should not be run";
        logger.log(Level.INFO, "User Data has been written to file");
    }

    /**
     * Loads the user career data from the previous session into a {@code UserCareerData} object which is returned to
     * resume previous session's activity.
     * If no previous data was stored, a new {@code UserCareerData} class is instantiated, data is reset.
     *
     * @return An object of user plans that contain previous user career data (if any).
     */
    @Override
    public UserCareerData loadUserData () {
        UserCareerData userCareerData;
        try {
            userCareerData = jsonUserDataLoader.loadFromJson(userFilePath);
            logger.log(Level.INFO, "Data has been restored from previous session!");
        } catch (DukeError e) {
            userCareerData = new UserCareerData();
            try {
                writeToJson(userCareerData);
                logger.log(Level.WARNING,
                           "Data file has been corrupted or missing, we will create a new file and reset " +
                               "your progress.");
            } catch (DukeError error) {
                System.out.println("Something went wrong saving your data!");
                logger.log(Level.SEVERE, "Unable to write new user data file to hard disk!");
            }
        }
        return userCareerData;
    }

}
