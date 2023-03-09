package seedu.duke.storage;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
//import com.google.gson.TypeAdapterFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*
//Importing classes from ui, parser and Duke.
 import seedu.duke.Parser
 import seedu.duke.ui.Ui
 import seedu.duke.Duke
 import seedu.duke.Event
 import seedu.duke.EventList
 */


public class Storage {
    //private final Ui ui = new Ui();
    private static final String fileLocation = System.getProperty("user.dir") + "/save/save.json";
    GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
    Gson gson = builder.create();
    /*
    public void saveToFile(ArrayList<Event> EventList){
        File saveFile = new File(fileLocation);
        String gsonData = gson.toJson(taskList);
        if (!saveFile.exists()){
            try {
                saveFile.createNewFile();
            }
            catch (IOException e){
                ui.showException("IOException");
            }

        }
        try{
            FileWriter taskWriter;
            taskWriter = new FileWriter(saveFile.getAbsoluteFile(),false);
            taskWriter.write(gsonData);
            taskWriter.close();
        }catch (IOException e){
            System.out.println("IOException");
        }
    }
     */

    /*
    public ArrayList<Event> loadTasks(){
        File saveFile = new File(fileLocation);
        ArrayList<Event> savedList = new ArrayList<>();
        if(!saveFile.exists()){
            return savedList;
        }
        InputStreamReader fileReader;
        try{
            fileReader = new InputStreamReader(new FileInputStream(saveFile), StandardCharsets.UTF_8);
            JsonReader gsonInterpreter = new JsonReader(fileReader);
            savedList = gson.fromJson(fileReader); //Placeholder.
        }catch (Exception e){
            System.out.println(e);
        }
        return savedList;
    }
     */
}