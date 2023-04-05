//@@author jeromeongithub
package seedu.todolist.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import seedu.todolist.exception.FailedLoadConfigException;
import seedu.todolist.exception.FailedLoadDataException;
import seedu.todolist.exception.FailedSaveException;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A class for saving the task list as a text file and loading it.
 */
public class Storage {
    public static final String DEFAULT_DATA_PATH = "./data.json";
    public static final String DEFAULT_CONFIG_PATH = "./config.json";
    private File dataFile;
    private File configFile;
    private Gson gson;

    public Storage() {
        this(DEFAULT_DATA_PATH, DEFAULT_CONFIG_PATH);
    }

    public Storage(String dataPath, String configPath) {
        assert dataPath != null : "NULL filepath was given";
        dataFile = new File(dataPath);
        configFile = new File(configPath);
        gson = new GsonBuilder().setPrettyPrinting().
                registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    }

    private void writeToFile(File file, String textToAdd) throws IOException {
        // Try-with-resources to auto close
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(textToAdd);
        }
    }

    /**
     * Writes the current task list to the local save file.
     *
     * @param taskList The task list being saved.
     * @throws FailedSaveException If the save file cannot be saved.
     */
    public void save(TaskList taskList, Config config) throws FailedSaveException {
        try {
            writeToFile(dataFile, gson.toJson(taskList));
            writeToFile(configFile, gson.toJson(config));
        } catch (IOException e) {
            throw new FailedSaveException();
        }
    }

    public TaskList loadData() throws FileNotFoundException, FailedLoadDataException {
        try {
            JsonReader reader = new JsonReader(new FileReader(dataFile));
            // if the file is not empty, set the task list as the saved task list
            TaskList taskList = gson.fromJson(reader, TaskList.class);
            if (taskList == null) {
                return new TaskList();
            }
            return taskList;
        } catch (JsonParseException | DateTimeParseException e) {
            throw new FailedLoadDataException();
        }
    }

    public Config loadConfig() throws FileNotFoundException, FailedLoadConfigException {
        try {
            JsonReader reader = new JsonReader(new FileReader(configFile));
            Config config = gson.fromJson(reader, Config.class);
            if (config == null) {
                return new Config();
            }
            return config;
        } catch (JsonParseException | DateTimeParseException e) {
            throw new FailedLoadConfigException();
        }
    }
}
