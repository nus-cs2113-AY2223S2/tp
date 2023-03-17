package seedu.duke.storage;

import java.util.ArrayList;
import seedu.duke.userdata.SessionData;

public class UserCareerData {

    private ArrayList<SessionData> totalUserCareerSessions;

    public UserCareerData () {
        totalUserCareerSessions = new ArrayList<>();
    }

    public ArrayList<SessionData> getTotalUserCareerSessions () {
        return totalUserCareerSessions;
    }

    public void setTotalUserCareerSessions (ArrayList<SessionData> totalUserCareerSessions) {
        this.totalUserCareerSessions = totalUserCareerSessions;
    }

    public void addWorkoutSession (SessionData sessionData) {
        totalUserCareerSessions.add(sessionData);
    }

}
