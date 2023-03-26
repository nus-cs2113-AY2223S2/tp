package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.Reader;
import seedu.duke.commons.exceptions.FileReadError;
import seedu.duke.data.userdata.userplan.Plan;
import seedu.duke.data.userdata.userplan.UserPlan;

/**
 * Class to read and parse the json file containing {@code userPlans} into an ArrayList of completed workouts.
 */
public class JsonUserPlansLoader {
    private final Gson gson;

    public JsonUserPlansLoader (Gson gson) {
        this.gson = gson;
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
