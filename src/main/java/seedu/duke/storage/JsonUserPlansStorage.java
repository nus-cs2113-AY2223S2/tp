package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.duke.commons.LogMaster;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.userplan.UserPlan;
//@@author EangJS

/**
 * This class is responsible to save all user plans into a json file for storage on the hard disk.
 */
public class JsonUserPlansStorage implements UserPlansStorage {
    private static final Logger logger = LogMaster.getLogger(JsonUserPlansStorage.class);
    private final String userPlansFilePath;
    private final Gson gson;
    private final JsonUserPlansLoader jsonUserPlansLoader;
    private final JsonUserPlansWriter jsonUserPlansWriter;

    /**
     * Constructs the Storage handler for user plans to convert into json format using the gson library.
     *
     * @param userPlansFilePath Desired file path of where user plans are stored.
     */
    public JsonUserPlansStorage (String userPlansFilePath) {
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();
        this.jsonUserPlansWriter = new JsonUserPlansWriter(gson);
        this.jsonUserPlansLoader = new JsonUserPlansLoader(gson);
        this.userPlansFilePath = userPlansFilePath;
    }

    /**
     * Writes the given user plans into json file which is saved on the hard disk.
     *
     * @param userPlan The user plan that is to be saved into the json file that is stored on the hard disk.
     * @throws DukeError Occurs when there is an error writing to the hard disk.
     */
    @Override
    public void writeToJson (UserPlan userPlan) throws DukeError {
        boolean isSaved = jsonUserPlansWriter.saveToJson(userPlansFilePath, userPlan);
        assert isSaved : "An exception should be thrown, this part of code should not be run";
        logger.log(Level.INFO, "User Plan has been written to file");
    }

    /**
     * Loads the userplans from the previous session into a UserPlan object which is returned to resume previous
     * activity. If no previous plans were stored, a new user plan class is instantiated, data is reset.
     *
     * @return An object of user plans that contain previous user plans (if any).
     */
    @Override
    public UserPlan loadUserPlans () {
        UserPlan userPlan;
        try {
            userPlan = jsonUserPlansLoader.loadPlanFromJson(userPlansFilePath);
            logger.log(Level.INFO, "Plans Data has been restored from the previous session!");
            return userPlan;
        } catch (Exception e) {
            userPlan = new UserPlan();
            try {
                writeToJson(userPlan);
                logger.log(Level.WARNING,
                           "Plans data file has been corrupted or missing, we will create a new file for " +
                               "you, all your plans will be lost.");
                System.out.println("Corrupted / Missing plans file, creating a new one.");
                return userPlan;
            } catch (DukeError ex) {
                logger.log(Level.SEVERE, "Unable to write new user data file to hard disk!");
                System.out.println("Something went wrong saving your reset plans data!");
            }
        }
        return userPlan;
    }

}
