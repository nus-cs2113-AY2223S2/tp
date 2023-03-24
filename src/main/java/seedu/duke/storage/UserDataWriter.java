package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import seedu.duke.exceptions.DukeError;
import seedu.duke.userdata.UserCareerData;

import java.io.FileWriter;
import java.io.Writer;
import seedu.duke.userplan.UserPlan;

/**
 * Class to write an ArrayList of completedWorkouts into a json file.
 */
public class UserDataWriter {
    protected final Gson gson;

    public UserDataWriter (Gson gson) {
        this.gson = gson;
    }

    /**
     * Takes in an Arraylist containing sessions (in user career data) by user and writes it into a jsonArray which
     * then saves it into a json file called userData.json.
     *
     * @param userFilePath File path in which user data is stored
     * @param userCareerData userCareerData ArrayList containing all userWorkoutHistory by the user.
     * @return returns a boolean value on the success of saving the file, true if success, false otherwise
     *
     * @throws DukeError Occurs when there is an error in writing the file
     */
    public boolean saveToJson (String userFilePath, UserCareerData userCareerData) throws DukeError {
        try {
            Writer writer = new FileWriter(userFilePath);
            JsonArray jsonArray = gson.toJsonTree(userCareerData.getTotalUserCareerSessions()).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("History", jsonArray);
            writer.write(gson.toJson(jsonObject));
            writer.close();
            return true;
        } catch (Exception e) {
            throw new DukeError("File Write Error");
        }
    }

    /**
     * Takes in the class of user plans and writes it into a jsonArray which is then saved into a json file called
     * plansData.json
     *
     * @param plansFilePath File path in which user plans are stored
     * @param userPlans The user plans containing all workout plans by the user
     * @return returns a boolean value on the success of saving the file, true if success, false otherwise
     *
     * @throws DukeError Occurs when there is an error in writing the file
     */
    public boolean saveToJson (String plansFilePath, UserPlan userPlans) throws DukeError {
        try {
            Writer writer = new FileWriter(plansFilePath);
            JsonArray jsonArray = gson.toJsonTree(UserPlan.getPlan()).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("UserPlans", jsonArray);
            writer.write(gson.toJson(jsonObject));
            writer.close();
            return true;
        } catch (IOException error) {
            throw new DukeError("Plans save error");
        }
    }

}
