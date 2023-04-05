//@@author jeromeongithub
package seedu.todolist.storage;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import seedu.todolist.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class for saving the task list as a text file and loading it.
 */
public class Storage {
    public static final String DEFAULT_SAVE_PATH = "./data.txt";
    private boolean isNewSave;
    private File file;
    private Gson gson;

    public Storage(String filepath) {
        assert filepath != null : "NULL filepath was given";
        file = new File(filepath);
        isNewSave = !file.exists();
        gson = new Gson();
    }

    // code provided by module website
    private void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    public boolean isNewSave() {
        return isNewSave;
    }

    /**
     * Writes the current task list to the local save file.
     *
     * @param taskList The task list being saved.
     * @throws IOException If the save file exists but is a directory rather than a regular file, does not exist but
                           cannot be created, or cannot be opened for any other reason.
     */
    public void saveData(TaskList taskList, String filepath) throws IOException {
        String json = gson.toJson(taskList);
        writeToFile(filepath, json);
    }



    /**
     * Loads the task list from the local save file, if it exists.
     *
     * @return The task list read from the save file, if it exists.
     * @throws FileNotFoundException If no save file is found.
     */
    public TaskList loadData(String filepath) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(reader, TaskList.class);
    }

}
