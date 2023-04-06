package seedu.duke.storage;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.userplan.UserPlan;
//@@author EangJS

/**
 * Represents a storage for {@code UserPlans}.
 */
public interface UserPlansStorage {
    /**
     * Saves the given {@code UserPlans} object to the storage.
     *
     * @param userPlan cannot be null but can be empty, contains all user plans of all sessions.
     * @throws DukeError Occurs when there is an error writing to the file.
     */
    void writeToJson (UserPlan userPlan) throws DukeError;

    /**
     * Loads the @{code UserPlans} from the hard disk.
     *
     * @return the {@code UserPlans} data from the previous session that is stored on the hard disk.
     */
    public UserPlan loadUserPlans ();

}
