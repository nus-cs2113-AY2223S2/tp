package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import seedu.duke.exceptions.DukeError;

import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;

/**
 * Class to write an ArrayList of completedWorkouts into a json file.
 */
public class UserDataWriter {
    private final Gson gson;

    public UserDataWriter (Gson gson) {
        this.gson = gson;
    }

    /**
     * Takes in an Arraylist containing sessions (in user career data) by user and writes it into a jsonArray which
     * then saves it into a
     * json file called userData.json.
     *
     * @param userCareerData ArrayList containing all userWorkoutHistory by the user.
     */
    public boolean saveToJson (String fileName, UserCareerData userCareerData) throws DukeError {
        try {
            Writer writer = new FileWriter(fileName);
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

}
