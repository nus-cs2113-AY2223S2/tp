package seedu.apollo.ui;

import seedu.apollo.task.Task;
import seedu.apollo.module.Module;
import seedu.apollo.task.TaskList;

import java.rmi.UnexpectedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * User Interface class that deals with inputs from and outputs to the user.
 */
public class Ui {

    // Scanner to read user inputs on CLI
    private static final Scanner in = new Scanner(System.in);

    /**
     * Get user input from CLI.
     *
     * @return String containing the CLI input.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints out a line divider.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        showLine();
        System.out.println("Hello from\n" +
                " ____    ____    _____  __      __       _____\n" +
                "|  _  | |  _ \\  | ___ | | |     | |     | ___ |\n" +
                "| |_| | | |_| | | | | | | |     | |     | | | |\n" +
                "| | | | |  __/  | |_| | | |___  | |___  | |_| |\n" +
                "|_| |_| |_|     \\_____/ |_____| |_____| \\_____/\n" +
                "Enter \"help\" to see a list of commands.");
        showLine();
    }

    /**
     * For {@code help} command.
     * Prints out a list of all available commands.
     */
    public void printHelpMessage() {
        System.out.println(" Enter \"list\" to see all tasks\n" +
                " Enter \"listmod\" to see your module list\n" +
                " Enter \"todo [task]\" to add a task\n" +
                " Enter \"deadline [task] /by [date]\" to add a deadline\n" +
                " Enter \"event [task] /from [date] /to [date]\" to add an event\n" +
                " Enter \"addmod [MODULE_CODE]\" to add a Module to the Module list\n" +
                " Enter \"mark [idx]\" to mark task as done\n" +
                " Enter \"unmark [idx]\" to mark task as not done\n" +
                " Enter \"delete [idx]\" to remove task from list\n" +
                " Enter \"delmod [MODULE_CODE]\" to remove a Module you previously added\n" +
                " Enter \"find [keyword]\" to see all tasks containing [keyword]\n" +
                " Enter \"date [yyyy-MM-dd]\" to see all tasks occurring on that date\n" +
                " Enter \"bye\" to exit the program\n\n" +
                " ***NOTE***\n" +
                " The \"date\" command only considers tasks when [date] is input in the format:\n" +
                "     \"yyyy-MM-ddThh:mm\"\n" +
                " eg. \"2023-10-30T23:59\" represents Oct 20 2023, 11:59PM");
    }

    /**
     * For {@code list} command.
     * Prints all Tasks within the TaskList given.
     *
     * @param allTasks TaskList of Tasks.
     */
    public void printList(TaskList allTasks) {
        if (allTasks.size() == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(i + 1 + "." + allTasks.get(i));
        }
    }

    /**
     * For {@code list} command.
     * Prints all Modules within the ArrayList given
     *
     * @param allModules ArrayList of Modules
     */
    public void printModuleList(ArrayList<Module> allModules) {
        if (allModules.size() == 0) {
            System.out.println("There are no modules in your module list!");
            return;
        }
        System.out.println("These are the modules you are taking this semester:");
        for (int i = 0; i < allModules.size(); i++) {
            System.out.println(i + 1 + "." + allModules.get(i).toString());
        }
    }

    /**
     * For {@code delmod} command.
     * Prints message for successful deletion of Module.
     *
     * @param moduleCode The code of the module which was deleted.
     */
    public void printModuleDeleteMessage(String moduleCode) {
        System.out.println("Got it, removed " + moduleCode + " from your Module list.");

    }

    /**
     * For {@code delmod} command.
     * Prints message if Module cannot be found for unsuccessful deletion of Module.
     *
     * @param moduleCode The code of the module which was not found.
     */
    public void printUnsuccessfulModuleDelete(String moduleCode) {
        System.out.println("Sorry, the module " + moduleCode + " does not exist in your Module list!");
    }

    /**
     * For {@code todo}, {@code deadline}, and {@code event} commands.
     * Prints out message for successful adding of Task.
     *
     * @param newTask Task that has just been added.
     */
    public void printAddMessage(Task newTask) {
        System.out.println("Got it. I've added this " + newTask.getType() + ":\n" +
                "  " + newTask);
    }

    public void printAddModuleMessage(Module newModule) {
        System.out.println("Got it. I've added this module:\n" +
                "  " + newModule);
    }

    /**
     * For {@code mark} command.
     * Prints out message for successful marking of Task as done.
     *
     * @param doneTask Task that has just been marked as done.
     */
    public void printMarkDone(Task doneTask) {
        System.out.println("Nice!, I've marked this task as done:\n" +
                "  " + doneTask);
    }

    /**
     * For {@code unmark} command.
     * Prints out message for successful marking of Task as not done.
     *
     * @param notDoneTask Task that has just been marked as not done.
     */
    public void printMarkNotDone(Task notDoneTask) {
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " + notDoneTask);
    }

    /**
     * For {@code delete} command.
     * Prints out message for successful deletion of Task.
     *
     * @param deletedTask Task that will be deleted.
     * @param size        Number of tasks left in the list after deletion.
     */
    public void printDeleted(Task deletedTask, int size) {
        System.out.println("Noted, I've removed this task:\n" +
                "  " + deletedTask + "\n" +
                "Now you have " + (size - 1) + " tasks in the list");
    }

    /**
     * For {@code find} command.
     * Prints all Tasks within the TaskList given, all containing a certain keyword.
     *
     * @param foundTasks TaskList of Tasks containing a keyword.
     */
    public void printFoundList(TaskList foundTasks) {
        if (foundTasks.size() == 0) {
            System.out.println("There are no matching tasks!");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println(i + 1 + "." + foundTasks.get(i));
        }
    }

    /**
     * For {@code date} command.
     * Prints all Tasks within the TaskList given, all happening on a certain date.
     *
     * @param happeningTasks TaskList of Tasks happening on a date.
     * @param date           Date that was used to shortlist the tasks.
     */
    public void printDateList(TaskList happeningTasks, LocalDate date) {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        if (happeningTasks.size() == 0) {
            System.out.println("There are no tasks on " + dateString + "!");
            return;
        }
        System.out.println("Here are the tasks happening on " + dateString + ":");
        for (int i = 0; i < happeningTasks.size(); i++) {
            System.out.println(i + 1 + "." + happeningTasks.get(i));
        }
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // error messages

    /**
     * Prints error message if the index entered does not fit the format.
     *
     * @param size Number of tasks within the current TaskList.
     */
    public void printErrorForIdx(int size) {
        boolean isEmptyTaskList = (size == 0);
        if (!isEmptyTaskList) {
            System.out.println("Please enter [idx] in the form of an integer from 1 to " + size);
        } else {
            System.out.println("There are no tasks in your list!");
        }
    }

    /**
     * Prints error message if reading or writing to the hard disk throws an IO error.
     */
    public void printErrorForIO() {
        System.out.println("Something went wrong with the hard disk :(");
    }

    /**
     * Prints error message if the deadline entered does not fit the format.
     */
    public void printInvalidDeadline() {
        System.out.println("Please enter deadline as \"deadline [task] /by [date]\".");
    }

    /**
     * Prints error message if the event entered does not fit the format.
     */
    public void printInvalidEvent() {
        System.out.println("Please enter event as \"event [task] /from [date] /to [date]\".");
    }

    /**
     * Prints error message if the date entered does not fit the format.
     */
    public void printInvalidDateTime() {
        System.out.println("Please enter date in the format of yyyy-MM-dd.");
    }

    /**
     * Prints error message if the command entered is not understood by Apollo.
     */
    public void printInvalidCommand() {
        System.out.println("Sorry, but I don't know what that means :(");
    }

    /**
     * Prints error message if there is corrupted data in the save file when initially reading from it.
     *
     * @param counter  Index of the line of the save file that is corrupted.
     * @param filePath The location at which the save file is stored.
     */
    public void printInvalidSaveFile(int counter, String filePath) {
        showLine();
        System.out.println("There is an error in save.txt at line " + (counter + 1) + "\n" +
                "Task " + (counter + 1) + " has been excluded. You can edit the save file at:\n" +
                filePath);
        showLine();
    }

    /**
     * Prints error message if the user does not specify the description of a task.
     */
    public void printEmptyDescription() {
        System.out.println("Oops! The description of a task cannot be empty.");
    }

    /**
     * Prints error message if the user does not specify the keyword of a search.
     */
    public void printEmptyKeyword() {
        System.out.println("Please specify a keyword to do the search with!");
    }

    /**
     * Prints error message if the start date of an even occurs after the end date.
     */
    public void printDateOrderException() {
        System.out.println("Oops, the start date for your event occurs after the end date!");
    }

    /**
     * Prints error message if an unexpected error occurs.
     *
     * @param unexpectedException Contains detail message saying where unexpected exception occurred.
     */
    public void printUnexpectedException(UnexpectedException unexpectedException) {
        System.out.println("Oh no... Something went wrong while doing the following: " +
                unexpectedException.getMessage() + "\nExiting Apollo...");
    }

    /**
     * Prints error message if the user tries to add a module which does not exist.
     */
    public void printInvalidModule() {
        System.out.println("This module does not exist!");
    }

    /**
     * Prints error message if the user does not specify the module to add.
     */
    public void printEmptyAddMod() {
        System.out.println("Please specify a module to add!");
    }

    /**
     * Prints error message if the user does not specify the module to delete.
     */
    public void printEmptyDelMod() {
        System.out.println("Please specify a module to delete!");
    }

    public void printDuplicateModule() {
        System.out.println("Module already added in Module List!");
    }

}
