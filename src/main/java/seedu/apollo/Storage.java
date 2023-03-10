package seedu.apollo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import seedu.apollo.exception.DateOrderException;
import seedu.apollo.exception.InvalidDeadline;
import seedu.apollo.exception.InvalidEvent;
import seedu.apollo.exception.InvalidSaveFile;
import seedu.apollo.module.Module;
import seedu.apollo.task.Deadline;
import seedu.apollo.task.Event;
import seedu.apollo.task.Task;
import seedu.apollo.task.TaskList;
import seedu.apollo.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that initialises the task list and updates the save file.
 */
public class Storage {
    // Location of save file
    protected static String filePath;

    private static final String DATAFILEPATH = "./data/data.json";

    /*
    Each task is saved as a line in the save file in this format:
        [type] | [status] | [description]
    Followed by:
        /by [date]                  for Deadlines or
        /from [date] /to [date]     for Events

    eg. E | X | holiday /from 2023-02-25T00:00:00 /to 2023-03-04T23:59:00
    */

    // ints indicating position of terms in each line of the save file
    private static final int TYPE_POS = 0;
    private static final int STATUS_POS = 4;
    private static final int PARAM_POS = 8;
    // chars representing type of Tasks within the save file
    private static final char TXT_TODO_WORD = 'T';
    private static final char TXT_DEADLINE_WORD = 'D';
    private static final char TXT_EVENT_WORD = 'E';

    /**
     * Initialise Storage class, set filePath.
     *
     * @param filePath Location of the local save file.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Overwrites the existing save file based on the current TaskList.
     *
     * @param tasks Contains all stored tasks.
     * @throws IOException If something goes wrong during the overwriting process.
     */
    public void update(TaskList tasks) throws IOException {
        FileWriter overwrite = new FileWriter(filePath);
        for (Task task : tasks.allTasks) {
            String desc = task.getDescription();
            String type = task.getType();
            String stat = task.getStatus();
            switch (type) {
            case "todo":
                overwrite.write("T | " + stat + " | " + desc + "\n");
                break;
            case "deadline":
                Deadline tempDeadline = (Deadline) task;
                String by = tempDeadline.getBy(Task.storePattern);
                overwrite.write("D | " + stat + " | " + desc + " /by " + by + "\n");
                break;
            case "event":
                Event tempEvent = (Event) task;
                String from = tempEvent.getFrom(Task.storePattern);
                String to = tempEvent.getTo(Task.storePattern);
                overwrite.write("E | " + stat + " | " + desc + " /from " + from + " /to " + to + "\n");
                break;
            default:
                throw new IOException();
            }
        }
        overwrite.close();
    }

    /**
     * Loads data from the save file into a new ArrayList of Tasks.
     * If save file is not found, creates a new save file and returns an empty ArrayList.
     *
     * @param ui Prints out error messages to user.
     * @return ArrayList of Tasks (containing data from save file / empty).
     * @throws IOException If save file is not found, and a new one cannot be created.
     */
    public ArrayList<Task> load(Ui ui) throws IOException {
        ArrayList<Task> newAllTasks = new ArrayList<>();
        File save = new File(filePath);
        try {
            newAllTasks = readFileContents(save, ui);
            return newAllTasks;
        } catch (FileNotFoundException e) {
            ui.printErrorFileNotFound();
            save.createNewFile();
            return newAllTasks;
        }
    }

    public ArrayList<Module> loadModuleList() throws FileNotFoundException {
        Type moduleDataType = new TypeToken<ArrayList<Module>>(){}.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(DATAFILEPATH));
        ArrayList<Module> moduleDataList = gson.fromJson(reader, moduleDataType);
        System.out.println("Module Data loaded from " + DATAFILEPATH);
        return moduleDataList;
    }

    /**
     * Reads all lines in the save file, initialises them as an ArrayList of Tasks.
     *
     * @param save Save file.
     * @return ArrayList of initialised Tasks based on uncorrupted data in save file.
     * @throws FileNotFoundException If the save file cannot be found at filePath.
     */
    private static ArrayList<Task> readFileContents(File save, Ui ui) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        ArrayList<Task> newArrayList = new ArrayList<>();
        int counter = 0;
        while (s.hasNext()) {
            try {
                newArrayList.add(newTask(s.nextLine()));
                counter++;
            } catch (InvalidSaveFile e) {
                ui.printInvalidSaveFile(counter, filePath);
            }
        }
        return newArrayList;
    }

    /**
     * Interprets a line from the save file, returns it as a new Task.
     *
     * @param text Line from the save file.
     * @return Corresponding Task to data stored in {@code text}.
     * @throws InvalidSaveFile If any line in the input data is not of the right format.
     */
    private static Task newTask(String text) throws InvalidSaveFile {
        char type = getType(text);
        Boolean isDone = isStatusDone(text);
        String param = getParam(text);
        switch (type) {
        case TXT_TODO_WORD:
            return newToDo(isDone, param);
        case TXT_DEADLINE_WORD:
            return newDeadline(isDone, param);
        case TXT_EVENT_WORD:
            return newEvent(isDone, param);
        default:
            throw new InvalidSaveFile();
        }
    }

    private static char getType(String text) {
        return text.charAt(TYPE_POS);
    }

    private static Boolean isStatusDone(String text) {
        return text.charAt(STATUS_POS) == 'X';
    }

    private static String getParam(String text) {
        return text.substring(PARAM_POS);
    }

    private static ToDo newToDo(Boolean isDone, String param) {
        ToDo newToDo = new ToDo(param);
        newToDo.setDone(isDone);
        return newToDo;
    }

    private static Deadline newDeadline(Boolean isDone, String param) throws InvalidSaveFile {
        final String[] paramAndBy;
        try {
            paramAndBy = Parser.parseDeadline(param);
        } catch (InvalidDeadline e) {
            throw new InvalidSaveFile();
        }
        Deadline newDeadline = new Deadline(paramAndBy[0], paramAndBy[1]);
        newDeadline.setDone(isDone);
        return newDeadline;
    }

    private static Event newEvent(Boolean isDone, String param) throws InvalidSaveFile {
        final String[] paramAndFromTo;
        try {
            paramAndFromTo = Parser.parseEvent(param);
        } catch (InvalidEvent e) {
            throw new InvalidSaveFile();
        }
        try {
            Event newEvent = new Event(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            newEvent.setDone(isDone);
            return newEvent;
        } catch (DateOrderException e) {
            throw new InvalidSaveFile();
        }
    }

}
