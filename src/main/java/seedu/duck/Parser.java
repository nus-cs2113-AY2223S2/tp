package seedu.duck;

import seedu.duck.task.SchoolClass;
import seedu.duck.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Returns boolean value of true if input String is an integer,
     * else returns boolean value of false
     *
     * @param word String input to check if it is an integer
     * @return true if input String is an integer, otherwise false
     */
    public static boolean isNumeric(String word) {
        int valueToConvert;
        try {
            valueToConvert = Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            // Empty catch block, since the only purpose is to return false
            // if try block fails.
        }
        return false;
    }

    /**
     * Processes the user input and executes the appropriate command
     *
     * @param tasks The array list of tasks
     * @param line The line of user input
     * @param in The input from scanner
     */
    static void processCommand(ArrayList<Task> tasks, PriorityQueue<SchoolClass> classes, String line, Scanner in)
            throws IOException {
        while (!line.equals("bye")) { // Exits the program if input is "bye"
            String[] words = line.split(" ");
            if (line.isBlank()) {
                Ui.emptyCommandMessage();
            } else if (line.equals("list")) {
                // List out all the tasks added
                Ui.list(tasks);
            } else if (line.equals("list classes")) {
                Ui.listClasses(classes);
            } else if (line.equals("help")) {
                // List out all the tasks added
                Ui.help();
            } else if (words[0].equals("list") && (words.length == 2) && (isNumeric(words[1]))) {
                //list out all tasks in x days in the future specified by the user
                Ui.printUpcomingTasks(tasks, words[1]);
            } else if (line.equals("upcoming class")) {
                Ui.displayNextUpcomingClass(tasks);
            } else if (words[0].equals("unmark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as not done
                TaskList.unmarkTask(tasks, words);
                Storage.trySave(tasks, classes);
            } else if (words[0].equals("mark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as done
                TaskList.markTask(tasks, words);
                Storage.trySave(tasks, classes);
            } else if (words[0].equals("delete") && (words.length == 2) && (isNumeric(words[1]))) {
                // Delete a task
                TaskList.deleteTask(tasks, words);
                Storage.trySave(tasks, classes);
            } else if (words[0].equals("remove") && words[1].equals("class")) {
                // Remove a class from class list
                TaskList.tryDeleteClass(classes, line);
                Storage.trySave(tasks, classes);
            } else if (words[0].equals("find") && (words.length > 1)) {
                // Find tasks that contain a keyword
                Ui.find(tasks, words);
            } else if (words[0].equals("purge")){
                // Find tasks that contain a keyword
                TaskList.purge(tasks, classes);
            } else if (words[0].equals("priority") && (words.length == 3)) {
                // Find tasks that contain a keyword
                TaskList.setPriority(tasks,words);
                Storage.trySave(tasks, classes);
            } else if (words[0].equals("clear")) {
                if (Ui.doubleCheck()) {
                    // Find tasks that contain a keyword
                    tasks.clear();
                    Task.clearCount();
                    Ui.borderLine();
                    System.out.println("\t Got it, all tasks have been cleared.");
                    Ui.borderLine();
                    Storage.clearTask();
                } else {
                    Ui.borderLine();
                    System.out.println("\t Quack! Process cancelled.");
                    Ui.borderLine();
                }
            } else {
                // Adding a task to the list
                TaskList.addTask(line, tasks, classes);
                Storage.trySave(tasks, classes);
            }
            line = in.nextLine();
        }
    }

    /**
     * Process the array of words from the user input and extracts the
     * keywords into a single string to use for the find function
     *
     * @param words The array of words generated from the user input
     * @return The keywords string to use for the find function
     */
    static String processKeywords(String[] words) {
        String rawKeyword = "";
        for (int i = 1; i < words.length; i++) {
            rawKeyword += (" " + words[i]);
        }
        String keyword = rawKeyword.trim();
        return keyword;
    }
}
