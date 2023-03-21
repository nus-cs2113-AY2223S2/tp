package seedu.duke.storage;

import seedu.duke.exceptions.DukeError;
import seedu.duke.userdata.UserCareerData;

/**
 * Interface for the handling of user data storage
 */
public interface Storage {
    public void writeToJson (UserCareerData userCareerData) throws DukeError;

    public UserCareerData loadUserCareer ();

}
