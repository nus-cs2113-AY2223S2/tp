package seedu.duke;

import seedu.duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Prints out all currently stored tasks in the list
     *
     * @param tasks The array list of tasks
     */
    static void list(ArrayList<Task> tasks) {
        int taskCount = Task.getTaskCount();
        borderLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
        borderLine();
    }

    /**
     * Finds tasks in the list that contain keywords input by the user
     *
     * @param tasks The array list of tasks
     * @param words The array of words generated from the user input
     */
    static void find(ArrayList<Task> tasks, String[] words) {
        ArrayList<Task> matchingResults = new ArrayList<>();
        int matchCount = 0;
        String keyword = Parser.processKeywords(words);

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingResults.add(task);
                matchCount++;
            }
        }
        printFindResults(matchingResults, matchCount);
    }

    /**
     * Prints the results of the find command
     *
     * @param matchingResults The array list of tasks that contain the keywords
     * @param matchCount The number of tasks in the list that contain the keywords
     */
    private static void printFindResults(ArrayList<Task> matchingResults, int matchCount) {
        if (matchingResults.isEmpty()) {
            noMatchMessage();
        } else {
            printMatchingList(matchingResults, matchCount);
        }
    }

    /**
     * Prints the list of tasks that contain the keywords
     *
     * @param matchingResults The array list of tasks that contain the keywords
     * @param matchCount The number of tasks in the list that contain the keywords
     */
    static void printMatchingList(ArrayList<Task> matchingResults, int matchCount) {
        borderLine();
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < matchCount; i++) {
            System.out.println("\t " + (i + 1) + "." + matchingResults.get(i));
        }
        borderLine();
    }

    /** Prints the border for opening or closing messages */
    static void borderLine() {
        System.out.println("\t____________________________________________________________");
    }

    private static void noMatchMessage() {
        borderLine();
        System.out.println("\t There are no matching tasks in your list.");
        borderLine();
    }

    static void emptyCommandMessage() {
        borderLine();
        System.out.println("\t Please enter a non-empty command.");
        borderLine();
    }

    static void addedTaskMessage(Task currentTask) {
        borderLine();
        System.out.println("\t Alright, I have added this task: \n\t\t" + currentTask);
        System.out.println("\t You now have " + (Task.getTaskCount() + 1) + " tasks in your list.");
        borderLine();
    }

    static void deleteTaskMessage(Task taskToDelete) {
        borderLine();
        System.out.println("\t Understood. I have removed this task:");
        System.out.println("\t\t" + taskToDelete);
        System.out.println("\t You now have " + Task.getTaskCount() + " tasks in your list.");
        borderLine();
    }

    static void exceedTaskNumberMessage(int taskNumber) {
        borderLine();
        System.out.println("\t Task " + taskNumber + " does not exist.");
        borderLine();
    }

    static void todoErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description.");
        borderLine();
    }

    static void eventErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description, start time and end time");
        borderLine();
    }

    static void deadlineErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid description and deadline.");
        borderLine();
    }

    /** Prints the startup message, includes instructions on available commands */
    static void greetingMessage() {
        borderLine();
        System.out.println("\t Hello! I'm Vivy.");
        System.out.println("\t Here are some commands you can give me:");
        System.out.println("\t - list: I'll list out all the tasks you have recorded.");
        System.out.println("\t - mark <task_number>: I'll mark that task as done.");
        System.out.println("\t - unmark <task_number>: I'll mark that task as undone.");
        System.out.println("\t - delete <task_number>: I'll delete that task from your list.");
        System.out.println("\t - find <keyword>: I'll find the tasks in your list that contain the keyword.");
        System.out.println("\t - bye: I will shut down my program.");
        System.out.println("\t - Anything else will be recorded as a task. \n");
        System.out.println("\t Format for tasks:");
        System.out.println("\t   Deadlines: <description> /by <deadline>");
        System.out.println("\t              (eg. Eat bread /by Thursday)");
        System.out.println("\t      Events: <description> /from <start date/time> /to <end date/time>");
        System.out.println("\t              (eg. Meeting /from March 3 8pm /to 9pm)");
        System.out.println("\t        Todo: <description>");
        System.out.println("\t              (eg. Water the plants) \n");
        System.out.println("\t What can I do for you?");
        borderLine();
    }

    /** Prints the exiting message when closing the program */
    static void exitMessage() {
        borderLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        borderLine();
    }
}