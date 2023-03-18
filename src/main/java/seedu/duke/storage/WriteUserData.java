package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;

/**
 * Class to write an ArrayList of completedWorkouts into a json file.
 */
public class WriteUserData {

    /**
     * Takes in an Arraylist containing sessions (in user career data) by user and writes it into a jsonArray which
     * then saves it into a
     * json file called userData.json.
     *
     * @param userCareerData ArrayList containing all userWorkoutHistory by the user.
     */
    public static void writeToJson (String fileName, UserCareerData userCareerData) {
        try {
            System.out.println("This is saved");
            Writer writer = new FileWriter(fileName);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            JsonArray jsonArray = gson.toJsonTree(userCareerData.getTotalUserCareerSessions()).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("History", jsonArray);
            writer.write(gson.toJson(jsonObject));
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
