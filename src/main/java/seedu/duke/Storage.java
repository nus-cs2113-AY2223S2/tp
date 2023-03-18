package seedu.duke;

import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// This class was largely based off the Storage classes from jeromeongithub/ip and erjunze/ip.
// The supporting code e.g. toSaveString() methods in TaskList and Task class were largely based off erjunze/ip.

/**
 * A class for saving the task list as a text file and loading it.
 */
public abstract class Storage {
    public static final String DELIMITER = "\u001D";

    /**
     * Writes the current task list to the local save file.
     *
     * @param filepath The path of the local save file.
     * @param taskList The task list being saved.
     * @param ui       The Ui object used in Duke to interact with the user.
     */
    public static void saveData(String filepath, TaskList taskList, Ui ui) throws IOException, NullPointerException {
        try {
            File f = new File(filepath); // not sure abt this line
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
        } catch (IOException e) {
            throw new IOException();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public static TaskList loadData(String filepath, Ui ui) throws NullPointerException, IOException,
                                                                   ClassNotFoundException {
        try {
            File f = new File(filepath);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList taskList = (TaskList) ois.readObject();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (IOException e) {
            throw new IOException();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }
}
