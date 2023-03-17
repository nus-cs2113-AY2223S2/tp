package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// This class was largely based off the Storage classes from jeromeongithub/ip and erjunze/ip.
// The supporting code e.g. toSaveString() methods in TaskList and Task class were largely based off erjunze/ip.

/**
 * A class for saving the task list as a text file and loading it.
 */
public abstract class Storage {
    public static final String DELIMITER = "\u001D";

    // code provided by module website
    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        assert filepath != null : "Invalid file path used.";
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the current task list to the local save file.
     *
     * @param filepath The path of the local save file.
     * @param taskList The task list being saved.
     * @param ui       The Ui object used in Duke to interact with the user.
     */
    public static void saveData(String filepath, TaskList taskList, Ui ui) throws IOException {
        try {
            writeToFile(filepath, taskList.toSaveString());
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public static TaskList loadData(String filepath, Ui ui) throws FileNotFoundException, ConversionErrorException {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            TaskList taskList = new TaskList();
            while (s.hasNext()) {
                String tasksInStringFormat = s.nextLine();
                taskList.addTask(convertStringToTask(tasksInStringFormat));
            }
            ui.listTasks(taskList);
            return taskList;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (ConversionErrorException e) {
            throw new ConversionErrorException();
        }
    }

    private static Task convertStringToTask(String taskString) throws ConversionErrorException {
        String[] splitTasks = taskString.split(DELIMITER);
        assert splitTasks.length == 3 : "Save string is split incorrectly.";
        assert splitTasks[0].equals("1") || splitTasks[0].equals("0") : "Save string has invalid completion status.";

        Task task;
        task = new Task(splitTasks[1], splitTasks[2]);
        task.setDone(splitTasks[0].equals("1"));
        return task;
    }
}
