package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.UserCareerData;

import java.io.FileWriter;
import java.io.Writer;
//@@author EangJS

/**
 * Class to write an ArrayList of completedWorkouts into a json file.
 */
public class JsonUserDataWriter {
    private final Gson gson;

    public JsonUserDataWriter (Gson gson) {
        this.gson = gson;
    }

    /**
     * Takes in an Arraylist containing sessions (in user career data) by user and writes it into a jsonArray which
     * then saves it into a json file called userData.json.
     *
     * @param userFilePath File path in which user data is stored.
     * @param userCareerData userCareerData ArrayList containing all userWorkoutHistory by the user.
     * @return returns a boolean value on the success of saving the file, true if success, false otherwise.
     *
     * @throws DukeError Occurs when there is an error in writing the file.
     */
    public boolean saveToJson (String userFilePath, UserCareerData userCareerData) throws DukeError {
        try (Writer writer = new FileWriter(userFilePath);) {
            JsonArray jsonArray = gson.toJsonTree(userCareerData.getTotalUserCareerSessions()).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("History", jsonArray);
            writer.write(gson.toJson(jsonObject));
            return true;
        } catch (Exception e) {
            throw new DukeError("File Write Error");
        }
    }

}
