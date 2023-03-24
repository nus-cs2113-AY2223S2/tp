package seedu.duke.storage;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.userdata.UserCareerData;

/**
 * Interface for the handling of user data storage
 */
public interface Storage {
    public void writeToJson (UserCareerData userCareerData) throws DukeError;

    public UserCareerData loadUserData ();

}
