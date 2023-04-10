package seedu.duke.storage;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.UserCareerData;
//@@author EangJS

/**
 * Represents a storage for {@code UserCareerData}.
 */
public interface UserCareerStorage {
    /**
     * Saves the given {@code UserCareerData} object to the storage.
     *
     * @param userCareerData cannot be null but can be empty, contains all user career data of all sessions.
     * @throws DukeError Occurs when there is an error writing to the file.
     */
    public void writeToJson (UserCareerData userCareerData) throws DukeError;

    /**
     * Loads the @{code UserCareerData} from the hard disk.
     *
     * @return the {@code UserCareerData} data from the previous session that is stored on the hard disk.
     */
    public UserCareerData loadUserData ();

}
