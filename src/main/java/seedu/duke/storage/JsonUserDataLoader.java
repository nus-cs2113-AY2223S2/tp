package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.userdata.IPPTSession;
import seedu.duke.data.userdata.Session;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.ui.ErrorMessages;

//@author EangJS

/**
 * Class to read and parse the json file containing userData into an ArrayList of completed workouts.
 */
public class JsonUserDataLoader {
    private final Gson gson;

    public JsonUserDataLoader (Gson gson) {
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
        try (Reader reader = new FileReader(userFilePath)) {
            JsonElement jsonTree = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("History");
            Session sessionFromFile = null;
            for (JsonElement element : jsonArray) {
                if(element.getAsJsonObject().has("userscore")){
                    sessionFromFile = gson.fromJson(element, IPPTSession.class);
                } else{
                    sessionFromFile = gson.fromJson(element, Session.class);
                    if (!sessionFromFile.checkSessionNullity()) {
                        throw new DukeError("Null error");
                    }
                }
                userCareerData.addWorkoutSession(sessionFromFile);
            }
            assert jsonArray.size() == userCareerData.getTotalUserCareerSessions()
                                                     .size() : "All elements from json must be written" +
                "into arrayList";
        } catch (Exception e) {
            throw new DukeError(ErrorMessages.ERROR_FILE_READ.toString());
        }
        return userCareerData;
    }

}
