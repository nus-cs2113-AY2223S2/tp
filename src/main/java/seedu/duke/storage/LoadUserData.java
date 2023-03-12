package seedu.duke.storage;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import seedu.duke.userdata.CompletedWorkout;


/**
 * Class to read and parse the json file containing userData into
 * an ArrayList of completed workouts
 */
public class LoadUserData {
    /**
     * Reads in the user data json file and parses the data into an
     * ArrayList of CompletedWorkouts
     *
     * @return ArrayList containing all CompletedWorkouts from the json file
     */
    public static ArrayList<CompletedWorkout> loadCompletedWorkouts(){
        ArrayList<CompletedWorkout> completedWorkouts = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setPrettyPrinting()
                .create();
        try{
            Reader reader = new FileReader("userData.json");
            JsonElement jsonTree = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("History");
            for (JsonElement element : jsonArray){
                completedWorkouts.add(gson.fromJson(element,CompletedWorkout.class));
            }
            assert jsonArray.size() == completedWorkouts.size() : "All elements from json must be written" +
                    "into arrayList";
        } catch (Exception e){
            System.out.println(e);
        }
        return completedWorkouts;
    }
}
