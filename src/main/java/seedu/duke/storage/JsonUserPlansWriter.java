package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.userplan.UserPlan;
//@@author EangJS

/**
 * Class to write all {@code UserPlans} into a json file.
 */
public class JsonUserPlansWriter {
    private Gson gson;

    public JsonUserPlansWriter (Gson gson) {
        this.gson = gson;
    }

    /**
     * Takes in the class of user plans and writes it into a jsonArray which is then saved into a json file called
     * plansData.json.
     *
     * @param plansFilePath File path in which user plans are stored.
     * @param userPlans The user plans containing all workout plans by the user.
     * @return returns a boolean value on the success of saving the file, true if success, false otherwise.
     *
     * @throws DukeError Occurs when there is an error in writing the file.
     */
    public boolean saveToJson (String plansFilePath, UserPlan userPlans) throws DukeError {
        try (Writer writer = new FileWriter(plansFilePath)) {
            JsonArray jsonArray = gson.toJsonTree(UserPlan.getPlan()).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("UserPlans", jsonArray);
            writer.write(gson.toJson(jsonObject));
            return true;
        } catch (IOException error) {
            throw new DukeError("Plans save error");
        }
    }

}
