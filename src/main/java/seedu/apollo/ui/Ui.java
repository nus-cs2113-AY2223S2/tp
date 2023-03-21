package seedu.apollo.ui;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.module.CalendarModule;
import seedu.apollo.module.LessonType;
import seedu.apollo.exception.task.DateOverException;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.Task;
import seedu.apollo.module.Module;
import seedu.apollo.task.TaskList;
import seedu.apollo.utils.LessonTypeUtil;

import java.rmi.UnexpectedException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

import static seedu.apollo.utils.DayTypeUtil.determineDay;

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
                "\n" +
                "Enter \"help\" to see a list of commands.");
        showLine();
    }

    /**
     * For {@code help} command.
     * Prints out a list of all available commands.
     */
    public void printHelpMessage() {
        System.out.println("\n" + " Enter \"list\" to see all tasks\n" +
                " Enter \"listmod\" to see your module list\n" +
                " Enter \"todo [task]\" to add a task\n" +
                " Enter \"deadline [task] /by [date]\" to add a deadline\n" +
                " Enter \"event [task] /from [date] /to [date]\" to add an event\n" +
                " Enter \"addmod [MODULE_CODE]\" to add a Module to the Module list\n" +
                " Enter \"addmod [MODULE_CODE] -[FLAG] [LESSON NUMBER]\" to add a lesson\n" +
                " Enter \"mark [idx]\" to mark task as done\n" +
                " Enter \"unmark [idx]\" to mark task as not done\n" +
                " Enter \"delete [idx]\" to remove task from list\n" +
                " Enter \"delmod [MODULE_CODE]\" to remove a Module you previously added\n" +
                " Enter \"week\" to see your schedule for the week\n" +
                " Enter \"find [keyword]\" to see all tasks containing [keyword]\n" +
                " Enter \"date [yyyy-MM-dd]\" to see all tasks occurring on that date\n" +
                " Enter \"bye\" to exit the program\n\n" +
                " ***NOTE***\n" +
                " Please enter all [date]s in the format: \"yyyy-MM-ddThh:mm\"\n" +
                " eg. \"2023-10-30T23:59\" represents Oct 20 2023, 11:59PM\n");
        printAddModuleOptions();
    }

    /**
     * Prints out a list of all available lesson types and their flags.
     */
    public void printAddModuleOptions() {
        System.out.println("There are -FLAGS for the various lessons options per module:\n" +
                "-lec\t\t\t" + "LECTURE\n" + "-plec\t\t\t" + "PACKAGED LECTURE\n" + "-st \t\t\t"
                + "SECTIONAL TEACHING\n" + "-dlec\t\t\t" + "DESIGN LECTURE\n" + "-tut\t\t\t" + "TUTORIAL\n"
                + "-ptut\t\t\t" + "PACKAGED TUTORIAL\n" + "-rcit\t\t\t" + "RECITATION\n" + "-lab\t\t\t"
                + "LABORATORY\n" + "-ws\t\t\t\t" + "WORKSHOP\n" + "-smc\t\t\t"
                + "SEMINAR STYLE MODULE CLASS\n" + "-mp\t\t\t\t" + "MINI PROJECT\n" + "-tt2\t\t\t"
                + "TUTORIAL TYPE 2\n");
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
        System.out.println("You have a total of " + allTasks.size() + " tasks in your tasklist.\n"
                + "Here are the tasks in your list:");
        int unmarkedTaskSize = 0;
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(i + 1 + "." + allTasks.get(i));
            if (!allTasks.get(i).isDone()) {
                unmarkedTaskSize += 1;
            }
        }
        System.out.println("There are " + unmarkedTaskSize + " unmarked tasks in your tasklist.");
    }

    /**
     * Prints out the user's schedule for the week.
     *
     * @param taskList Contains details about the user's tasks during the week.
     * @param calendar Contains details about the user's lessons during the week.
     */
    public void printWeek(TaskList taskList, Calendar calendar) {
        ZoneId zid = ZoneId.of("Asia/Singapore");
        LocalDate now = LocalDate.now(zid);
        LocalDate startWeek = now.with(DayOfWeek.MONDAY);
        LocalDate endWeek = now.with(DayOfWeek.SUNDAY);
        LocalDate curr = startWeek;
        System.out.println("Here's your week from " + startWeek + " to " + endWeek + ":");
        for (int i = 0; i<7; i++) {
            System.out.println("______________________");
            DayOfWeek day = determineDay(i);
            System.out.println(day + "\n");

            printLessonsOnDay(calendar, i);
            printTasksOnDay(taskList, curr);

            curr = curr.plusDays(1);
        }
    }

    private static void printLessonsOnDay(Calendar calendar, int i) {
        System.out.println("Lessons:");
        int count = 1;
        for (CalendarModule module : calendar.get(i)) {
            System.out.println(count + ". " + module.getCode() + " " + module.getSchedule());
            count++;
        }
    }

    private static void printTasksOnDay(TaskList taskList, LocalDate curr) {
        System.out.println("\nTasks:");
        int count = 1;
        for (Task task : taskList) {
            if (task.isOnDate(curr)) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    /**
     * For {@code list} command.
     * Prints all Modules within the ArrayList given
     *
     * @param allModules ArrayList of Modules
     */
    public void printModuleList(ModuleList allModules) {
        if (allModules.size() == 0) {
            System.out.println("There are no modules in your module list!");
            return;
        }
        System.out.println("You are taking " + allModules.size() + " module(s) this semester:");
        for (int i = 0; i < allModules.size(); i++) {
            System.out.printf("%d.%s (%s MCs)%n", i + 1, allModules.get(i).toString(),
                    allModules.get(i).getModuleCredits());
        }
        printTotalModularCredits(allModules);
    }

    /**
     * For {@code delmod} command.
     * Prints message for successful deletion of Module.
     *
     * @param moduleCode The code of the module which was deleted.
     */
    public void printModuleDeleteMessage(String moduleCode, ModuleList moduleList) {
        System.out.println("Got it, removed " + moduleCode.toUpperCase() + " from your Module list.");
        printTotalModularCredits(moduleList);
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

    /**
     * For {@code addmod} command.
     * Prints out message for successful adding of Module.
     *
     * @param newModule Module that has just been added.
     */
    public void printAddModuleMessage(Module newModule, ModuleList allModules, ArrayList<LessonType> lessonTypes) {
        System.out.println("Got it. I've added this module:\n" +
                "  " + newModule);
        printTotalModularCredits(allModules);
        System.out.println("Enter \"addmod " + newModule.getCode() + " -[FLAG] [LESSON NUMBER]\" " +
                "to add lessons for this module.");
        printLessonTypeMessage(lessonTypes);
    }

    /**
     * For {@code addmod, delmod, listmod} commands.
     * Prints out total modular credits of all modules in the ModuleList.
     *
     * @param allModules ArrayList of Modules
     */
    public void printTotalModularCredits(ModuleList allModules) {
        int moduleCredits = allModules.getTotalModuleCredits();
        System.out.println("Total modular credits you have in this semester: " + moduleCredits);
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
    public void printFoundList(ArrayList<Task> foundTasks) {
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
     * Prints error message if the index entered does not fit the format.
     *
     * @param size Number of modules within the current ModuleList.
     */
    public void printErrorForModIdx(int size) {
        boolean isEmptyModuleList = (size == 0);
        if (!isEmptyModuleList) {
            System.out.println("Please enter [idx] in the form of an integer from 1 to " + size);
        } else {
            System.out.println("There are no modules in your list!");
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
     * For new Deadline, Event.
     */
    public void printInvalidDateTime() {
        System.out.println("Please enter [date]s in the format of yyyy-MM-ddThh:mm.\n" +
                "eg. \"2023-10-30T23:59\" for Oct 20 2023, 11:59PM");
    }

    /**
     * Prints error message if the date entered does not fit the format.
     * For Date command.
     */
    public void printInvalidDate() {
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
     * Prints error message if the task being added occurs before the current date.
     *
     * @param exception Contains details about the task that was not added successfully.
     */
    public void printDateOverException(DateOverException exception) {
        System.out.println("Oops, your " + exception + " occurs before today!");
    }

    /**
     * Prints error message if the task being loaded from hard disk occurs before the current date.
     *
     * @param exception Contains details about the task that was not added successfully.
     */
    public void printExistingDateOver(DateOverException exception) {
        System.out.println("Deleting old " + exception);
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

    /**
     * Prints warning that the module being added by the user has been added before.
     *
     * @param module The module being added.
     */
    public void printDuplicateModule(Module module) {
        System.out.println("Module already added in Module List!");
        System.out.println("Enter \"addmod " + module.getCode() + " -[FLAG] [LESSON NUMBER]\" " +
                "to add lessons for this module.");
    }

    /**
     * Prints the available lesson types for that module.
     *
     * @param lessonTypes List of lesson types for that module.
     */
    public void printLessonTypeMessage(ArrayList<LessonType> lessonTypes) {
        if (lessonTypes.size() == 0) {
            System.out.println("This module has no lessons.");
            return;
        }
        System.out.println("Here are the lesson types for this module:");
        lessonTypes.sort(Comparator.comparing(Enum::toString));

        for (LessonType lessonType : lessonTypes) {
            System.out.println(LessonTypeUtil.enumToString(lessonType, true));
        }
    }

    /**
     * Prints message when lesson is added to a timetable.
     *
     * @param moduleCode Module code of the module whose lesson is being added.
     * @param lessonType Type of lesson being added.
     * @param classNumber Class number of the lesson being added.
     */
    public void printClassAddedMessage(String moduleCode, LessonType lessonType, String classNumber) {
        System.out.println("Adding lesson type: " + lessonType + " for Module: " + moduleCode);
        System.out.println("Class Number: " + classNumber);
    }

    /**
     * Prints message when lesson is Invalid.
     *
     */
    public void printInvalidLessonType() {
        System.out.println("This lesson type does not exist!");
    }

    /**
     * Prints message when lesson has already been added to the timetable.
     *
     */
    public void printLessonExists() {
        System.out.println("This lesson type already exists for this lesson!");
    }

    /**
     * Prints message when lesson has not been added to the timetable.
     *
     */
    public void printClassNotAdded() {
        System.out.println("This class has not been added to your timetable!");
    }

    /**
     * Prints message when lesson is deleted from the timetable.
     *
     * @param moduleCode Module code of the module whose lesson is being deleted.
     * @param lessonType Type of lesson being deleted.
     * @param lessonNumber Class number of the lesson being deleted.
     */
    public void printModuleLessonDeleteMessage(String moduleCode, LessonType lessonType, String lessonNumber) {
        System.out.println("Deleting lessons for module: " + moduleCode.toUpperCase());
        System.out.println("Lessons Deleted: " + lessonType + " - " + lessonNumber);
    }
}
