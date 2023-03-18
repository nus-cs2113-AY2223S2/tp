package seedu.duke.storage;

import java.util.ArrayList;
import seedu.duke.userdata.Session;

/**
 * This class consists of all user sessions that the user has completed in FitnessDuke and handles the addition of
 * new sessions made by the user
 */
public class UserCareerData {

    /**
     * ArrayList of Session that consists of all sessions completed by the user
     */
    private ArrayList<Session> totalUserCareerSessions;

    /**
     * Constructs an empty userCareer
     */
    public UserCareerData () {
        totalUserCareerSessions = new ArrayList<>();
    }

    /**
     * Gets all the user sessions completed in their career
     *
     * @return An arrayList consisting of all sessionData completed by the user
     */
    public ArrayList<Session> getTotalUserCareerSessions () {
        return totalUserCareerSessions;
    }

    /**
     * Adds a new workout session that has been completed by the user
     *
     * @param session Data of the session that has been completed
     */
    public void addWorkoutSession (Session session) {
        totalUserCareerSessions.add(session);
    }

}
