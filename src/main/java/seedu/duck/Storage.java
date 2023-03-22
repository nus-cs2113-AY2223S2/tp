package seedu.duck;


import seedu.duck.task.Deadline;
import seedu.duck.task.Event;
import seedu.duck.task.SchoolClass;
import seedu.duck.task.Task;
import seedu.duck.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    static final String SAVEPATH = "data/savedata.txt";
    static final String SAVEFOLDER = "data";

    /**
     * Load tasks from save data into the list
     *
     * @param line The line of input from the save file
     * @param tasks The array list of tasks
     */
    static void loadTask(String line, ArrayList<Task> tasks) {
        if (line.contains("/by")) {
            loadDeadline(line, tasks);
        } else if (line.contains("/class")) {
            loadSchoolClass(line, tasks);
        } else if (line.contains("/from") || line.contains("/to")) {
            loadEvent(line, tasks);
        } else {
            loadTodo(line, tasks);
        }
    }
    static void clearTask() throws IOException {
        {

            try {

                FileWriter fw = new FileWriter(SAVEPATH, false);

                PrintWriter pw = new PrintWriter(fw, false);

                pw.flush();

                pw.close();

                fw.close();

            } catch (Exception exception) {

                System.out.println("Exception have been caught");

            }

        }
    }

    /**
     * Adds a _Todo_ into the list without generating messages,
     * to be used when loading from save data.
     *
     * @param line The line of input from the save file
     * @param tasks The array list of tasks
     */
    static void loadTodo(String line, ArrayList<Task> tasks) {
        String description = line.substring(0, line.indexOf("<p>")).trim();
        Todo currTodo = new Todo(description);
        String priority = line.substring(line.indexOf("<p>") + 3).trim();
        currTodo.setPriority(priority);
        tasks.add(currTodo);
    }

    /**
     * Adds an event into the list without generating messages,
     * to be used when loading from save data.
     *
     * @param line The line of input from the save file
     * @param tasks The array list of tasks
     */
    static void loadEvent(String line, ArrayList<Task> tasks) {
        String description = line.substring(0, line.indexOf("/from")).trim();
        String start = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
        String end = line.substring(line.indexOf("/to") + 3, line.indexOf("<p>")).trim();
        String priority = line.substring(line.indexOf("<p>") + 3,line.indexOf("<p>") + 4).trim();
        Event currEvent = new Event(description, start, end);
        currEvent.setPriority(priority);
        tasks.add(currEvent);
    }

    /**
     * Adds a schoolClass to the list without generating messages,
     * to be used when loading from save data.
     *
     * @param line The line of input from the user
     * @param tasks The array list of tasks
     */
    static void loadSchoolClass(String line, ArrayList<Task> tasks) {
        String description = line.substring(0, line.indexOf("/class")).trim();
        String className = line.substring(line.indexOf("/class") + 6, line.indexOf("/from")).trim();
        String startString = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
        String endString = line.substring(line.indexOf("/to") + 3, line.indexOf("<p>")).trim();
        String priority = line.substring(line.indexOf("<p>") + 3,line.indexOf("<p>") + 4).trim();
        SchoolClass currSchoolClass = new SchoolClass(className, description, startString, endString);
        currSchoolClass.setPriority(priority);
        tasks.add(currSchoolClass);
    }

    /**
     * Adds a deadline into the list without generating messages,
     * to be used when loading from save data.
     *
     * @param line The line of input from the save file
     * @param tasks The array list of tasks
     */
    static void loadDeadline(String line, ArrayList<Task> tasks) {
        String description = line.substring(0, line.indexOf("/by")).trim();
        String deadline = line.substring(line.indexOf("/by") + 3, line.indexOf("<p>")).trim();
        String priority = line.substring(line.indexOf("<p>") + 3,line.indexOf("<p>") + 4).trim();
        Deadline currDeadline = new Deadline(description, deadline);
        currDeadline.setPriority(priority);
        tasks.add(currDeadline);
    }

    /**
     * Load the task status of a task from the save data
     *
     * @param tasks The array list of tasks
     * @param doneStatus The done status of the current task
     */
    static void loadTaskStatus(ArrayList<Task> tasks, String doneStatus) {
        int taskNumber = Task.getTaskCount();
        if (doneStatus.equals("1")) {
            tasks.get(taskNumber).markAsDone();
        } else {
            tasks.get(taskNumber).markAsNotDone();
        }
    }

    /**
     * Saves the tasks in the list to the save file
     *
     * @param tasks The array list of tasks
     */
    static void save(ArrayList<Task> tasks) throws IOException {
        File f = new File(SAVEPATH);
        if (f.exists()) {
            f.delete();
        }
        f.createNewFile();

        FileWriter fw = new FileWriter(SAVEPATH);
        for (Task currTask : tasks) {
            fw.write(currTask.toSaveString());
        }
        fw.close();
    }

    /**
     * Try to save, shows error message if saving fails
     *
     * @param tasks The array list of tasks
     */
    static void trySave(ArrayList<Task> tasks) {
        try {
            save(tasks);
        } catch (IOException e) {
            System.out.println("Saving error.");
        }
    }

    /**
     * Load the save data
     *
     * @param tasks The array list of tasks
     */
    static void load(ArrayList<Task> tasks) throws IOException {
        File folder = new File(SAVEFOLDER);
        if (!folder.exists()) {
            new File(SAVEFOLDER).mkdir();
        }

        File f = new File(SAVEPATH);
        if (!f.exists()) {
            f.createNewFile();
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] formattedInput = line.split(" ");
            String doneStatus = formattedInput[0];
            String command = "";
            for (int i = 1; i < formattedInput.length; i++) {
                command += formattedInput[i];
                command += " ";
            }
            loadTask(command, tasks);
            loadTaskStatus(tasks, doneStatus);
            Task.incrementCount();
        }
    }

    /**
     * Try to load the save data, shows error message if loading fails
     *
     * @param tasks The array list of tasks
     */
    static void tryLoad(ArrayList<Task> tasks) {
        try {
            load(tasks);
        } catch (IOException e) {
            System.out.println("Error loading save file.");
        }
    }
}
