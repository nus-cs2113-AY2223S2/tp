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
import seedu.duke.userdata.Session;

/**
 * Class to read and parse the json file containing userData into an ArrayList of completed workouts.
 */
public class LoadUserData {

    /**
     * Reads in the user career data json file and parses the data into an ArrayList of Session.
     * All elements must be written into the json file
     *
     * @return ArrayList containing all CompletedWorkouts from the json file.
     *
     * @throws DukeError when there is an error in reading data from the json file.
     */
    public static UserCareerData loadUserCareer (String fileName) throws DukeError {
        UserCareerData userCareerData = new UserCareerData();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        try {
            Reader reader = new FileReader(fileName);
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

}
