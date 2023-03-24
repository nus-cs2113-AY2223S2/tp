package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.commons.exceptions.FileReadError;
import seedu.duke.model.userdata.Session;
import seedu.duke.model.userdata.UserCareerData;
import seedu.duke.model.userdata.userplan.Plan;
import seedu.duke.model.userdata.userplan.UserPlan;

/**
 * Class to read and parse the json file containing userData into an ArrayList of completed workouts.
 */
public class UserDataLoader {
    private static final Integer DAYSINAWEEK = 7;
    private final Gson gson;

    public UserDataLoader (Gson gson) {
        this.gson = gson;
    }

    /**
     * Reads in the user career data json file and parses the data into an ArrayList of Session.
     * All elements must be written into the json file
     *
     * @return ArrayList containing all CompletedWorkouts from the json file.
     *
     * @throws DukeError when there is an error in reading data from the json file.
     */
    public UserCareerData loadFromJson (String userFilePath) throws DukeError {
        UserCareerData userCareerData = new UserCareerData();
        try {
            Reader reader = new FileReader(userFilePath);
            JsonElement jsonTree = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("History");
            for (JsonElement element : jsonArray) {
                userCareerData.addWorkoutSession(gson.fromJson(element, Session.class));
            }
            assert jsonArray.size() == userCareerData.getTotalUserCareerSessions()
                                                     .size() : "All elements from json must be written" +
                "into arrayList";
            reader.close();
        } catch (Exception e) {
            throw new FileReadError();
        }
        return userCareerData;
    }

    /**
     * Loads user plans from json folder and parses the data into an arraylist of plans in a day and adds into a new
     * user plan class
     *
     * @param plansFilePath file name in which the user plans are stored
     * @return returns the UserPlan class for the week
     *
     * @throws FileReadError Occurs when there is an error in reading the user plans file
     */
    public UserPlan loadPlanFromJson (String plansFilePath) throws FileReadError {
        UserPlan userPlan = new UserPlan();
        try {
            Reader reader = new FileReader(plansFilePath);
            JsonElement jsonTree = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("UserPlans");
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonWeekElements = jsonArray.get(i);
                JsonArray jsonDayPlans = jsonWeekElements.getAsJsonArray();
                for (JsonElement element : jsonDayPlans) {
                    userPlan.addDayPlan(gson.fromJson(element, Plan.class), i);
                }
            }
            return userPlan;
        } catch (Exception e) {
            throw new FileReadError();
        }
    }

}
