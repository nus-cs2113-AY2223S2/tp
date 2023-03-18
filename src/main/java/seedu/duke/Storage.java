package seedu.duke;

import seedu.duke.exception.FailedLoadException;
import seedu.duke.exception.FailedSaveException;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// The implementation for this class was learned from https://www.baeldung.com/java-serialization
/**
 * A class for saving the task list as a text file and loading it.
 */
public class Storage {
    public static final String DELIMITER = "\u001D";

    /**
     * Writes the current task list to the local save file.
     *
     * @param filepath The path of the local save file.
     * @param taskList The task list being saved.
     * @param ui       The Ui object used in Duke to interact with the user.
     */
    public void saveData(String filepath, TaskList taskList, Ui ui) throws FailedSaveException {
        try {
            File f = new File(filepath);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
        } catch (Exception e) {
            throw new FailedSaveException();
        }
    }

    public TaskList loadData(String filepath, Ui ui) throws FailedLoadException {
        try {
            File f = new File(filepath);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList taskList = (TaskList) ois.readObject();
            return taskList;
        } catch (Exception e) {
            throw new FailedLoadException();
        }
    }
}
