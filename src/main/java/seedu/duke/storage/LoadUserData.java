package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exceptions.FileReadError;
import seedu.duke.userdata.SessionData;

/**
 * Class to read and parse the json file containing userData into an ArrayList of completed workouts.
 */
public class LoadUserData {

    /**
     * Reads in the user data json file and parses the data into an ArrayList of SessionData.
     *
     * @return ArrayList containing all CompletedWorkouts from the json file.
     *
     * @throws DukeError when there is an error in reading data from the json file.
     */
    public static UserCareerData loadUserCareer () throws DukeError {
        UserCareerData userCareerData = new UserCareerData();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        try {
            Reader reader = new FileReader("userData.json");
            JsonElement jsonTree = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("History");
            for (JsonElement element : jsonArray) {
                userCareerData.addWorkoutSession(gson.fromJson(element, SessionData.class));
            }
            assert jsonArray.size() == userCareerData.getTotalUserCareerSessions()
                                                     .size() : "All elements from json must be written" +
                    "into arrayList";
        } catch (Exception e) {
            throw new FileReadError();
        }
        return userCareerData;
    }

}
