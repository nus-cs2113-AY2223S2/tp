package seedu.duke.storage;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.data.userdata.userplan.UserPlan;
//@@author EangJS

/**
 * Manages storage of Fitness Duke data into the local hard disk.
 */
public class StorageManager implements Storage {
    private UserCareerStorage userCareerStorage;
    private UserPlansStorage userPlansStorage;

    /**
     * Creates {@code StorageManager} with the given {@code UserCareerStorage} and {@code UserPlansStorage}.
     *
     * @param userCareerStorage Interface to handle all user career data.
     * @param userPlansStorage Interface to handle all user plans data.
     */
    public StorageManager (UserCareerStorage userCareerStorage, UserPlansStorage userPlansStorage) {
        this.userCareerStorage = userCareerStorage;
        this.userPlansStorage = userPlansStorage;
    }

    /**
     * Writes the given user career data object into a json file.
     *
     * @param userCareerData User career data to be saved into json.
     * @throws DukeError Occurs when there is a file write error.
     */
    @Override
    public void writeToJson (UserCareerData userCareerData) throws DukeError {
        userCareerStorage.writeToJson(userCareerData);
    }

    /**
     * Writes the given user plans object into a json file.
     *
     * @param userPlan User plans to be saved into json file.
     * @throws DukeError Occurs when there is a file write error.
     */
    @Override
    public void writeToJson (UserPlan userPlan) throws DukeError {
        userPlansStorage.writeToJson(userPlan);
    }

    /**
     * Loads the user career data from the previous session if any from the hard disk.
     *
     * @return the previous session's user career data.
     */
    @Override
    public UserCareerData loadUserData () {
        return userCareerStorage.loadUserData();
    }

    /**
     * Loads the user plans data from the previous session if any from the hard disk.
     *
     * @return the previous session's user plans data.
     */
    @Override
    public UserPlan loadUserPlans () {
        return userPlansStorage.loadUserPlans();
    }

}
