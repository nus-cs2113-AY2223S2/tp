package seedu.duke.storage;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import seedu.duke.Event;
import seedu.duke.EventList;
import seedu.duke.Ui;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Storage {

    private static final String fileLocation = System.getProperty("user.dir") + "/save.json";

    GsonBuilder builder = new GsonBuilder().registerTypeAdapter(ArrayList.class, new EventListAdapter())
            .setPrettyPrinting();
    Gson gson = builder.create();

    /**
     * Saves an EventList object's Array List to a file with JSON formatting.
     * @param eventList The EventList object that needs to be saved.
     */
    public void saveToFile(EventList eventList) {
        File saveFile = new File(fileLocation);
        String gsonData = gson.toJson(eventList.getFullList());
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                Ui.printErrorMsg("IOException occurred while creating new save file.");
            }
        }
        try {
            FileWriter taskWriter;
            taskWriter = new FileWriter(saveFile.getAbsoluteFile(), false);
            taskWriter.write(gsonData);
            taskWriter.close();
        } catch (IOException e) {
            Ui.printErrorMsg("IOException occurred while writing to file");
        }
    }

    /**
     * Loads and deserializes information from save.json into an ArrayList of events.
     * @return ArrayList of Event class containing information of previous load state
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Event> loadEvents() {
        File saveFile = new File(fileLocation);
        ArrayList<Event> savedList = new ArrayList<>();
        if (!saveFile.exists()) {
            return savedList;
        }
        InputStreamReader fileReader;
        try {
            fileReader = new InputStreamReader(new FileInputStream(saveFile), StandardCharsets.UTF_8);
            savedList = gson.fromJson(fileReader, ArrayList.class); //Placeholder.
        } catch (Exception e) {
            Ui.printErrorMsg("IOException occured while reading from save.json. It is likely corrupted");
        }
        return savedList;
    }
}
