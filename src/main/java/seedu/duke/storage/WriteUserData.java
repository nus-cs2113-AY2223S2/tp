package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import seedu.duke.userdata.TodoWorkout;

/**
 * Class to write an ArrayList of completedWorkouts into a json file.
 */
public class WriteUserData {

    /**
     * Takes in an Arraylist containing todoWorkouts by user and writes it into a jsonArray which then saves it into a
     * json file called userData.json.
     *
     * @param todoWorkouts ArrayList containing all todoWorkouts by the user.
     */
    public static void writeToJson(ArrayList<TodoWorkout> todoWorkouts) {
        try {
            Writer writer = new FileWriter("userData.json");
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            JsonArray jsonArray = gson.toJsonTree(todoWorkouts).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("History", jsonArray);
            writer.write(gson.toJson(jsonObject));
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
