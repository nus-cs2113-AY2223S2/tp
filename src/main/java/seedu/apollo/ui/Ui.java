package seedu.apollo.ui;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.task.DateOverException;
import seedu.apollo.module.CalendarModule;
import seedu.apollo.module.LessonType;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.module.Timetable;
import seedu.apollo.task.Task;
import seedu.apollo.task.TaskList;
import seedu.apollo.utils.LessonTypeUtil;

import java.rmi.UnexpectedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

import static seedu.apollo.calendar.SemesterUtils.getWeekNumber;
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
    public void showSmallLine() {
        System.out.println("_____________________________");
    }

    private void printApolloLogo() {
        System.out.println("Hello from\n" +
                " ____    ____    _____  __      __       _____\n" +
                "|  _  | |  _ \\  | ___ | | |     | |     | ___ |\n" +
                "| |_| | | |_| | | | | | | |     | |     | | | |\n" +
                "| | | | |  __/  | |_| | | |___  | |___  | |_| |\n" +
                "|_| |_| |_|     \\_____/ |_____| |_____| \\_____/\n" +
                "\n" +
                "Your personal task and timetable manager!\n" +
                "Enter \"help\" to see a list of commands.");
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        showLine();
        printApolloLogo();
        showLine();
    }

    /**
     * For {@code help} command.
     * Prints out a list of all available commands.
     */
    public void printHelpMessage() {
        printHelpCommandOptions();
        printTaskCommands();
        showLine();
        printModuleCommands();
        printNote();
        showLine();
        printUtility();

    }

    private void printHelpCommandOptions(){
        System.out.println("The help menu gives a summary of all the commands available in Apollo!\n" +
                "Input `help` to see all available commands."+
                "Input \"help [COMMAND]\" for usage help and more information for a specific command.\n");
    }

    public void printHelpCommandHelpMessage(){
        System.out.println("The help menu gives a summary of all the commands available in Apollo!\n" +
                "Format: help\n" +
                "For specific command help:\n"+
                "Format: help [COMMAND]\n" + "Example: help showmod");
    }
    private void printTaskCommands(){
        System.out.print("These are the available Task Commands and their corresponding commands (in brackets):\n\n" +
                "1. `list` - Track and organises your tasklist!\n" +
                "2. `todo [TASK]` - Adds a ToDo in your tasklist.\n" +
                "3. `deadline [TASK] -[BY]` - Adds a Deadline in your tasklist.\n" +
                "4. `event [TASK] -[FROM] -[TO]` - Adds an Event in your tasklist.\n" +
                "5. `mark [IDX]` - Marks a task in your tasklist as done!\n" +
                "6. `unmark [IDX]` - Unmarks a task in your tasklist as incomplete.\n" +
                "7. `delete [IDX]` - Deletes a task from your list.\n" +
                "8. `find [KEYWORD]` - Shows all tasks that contain a specified keyword.\n" +
                "9. `date [DATE]` - Shows all tasks that occur on the specified date.\n\n");
    }

    private void printModuleCommands(){
        System.out.print("These are the available Module Commands and their corresponding commands (in brackets):\n\n" +
                "1. `listmod` - Track and organise your academic plan for this semester!\n" +
                "2. `listmod [MODULE_CODE]` - See more information about the classes you've added " +
                "for a module in your list.\n"+
                "3. `listmod [MODULE_CODE] -[FLAG]` - See more information about a specific class type " +
                "for a module in your list\n"+
                "4. `showmod [MODULE_CODE]` - " +
                "See more information about the specified module.\n"
                +
                "5. `showmod [MODULE_CODE] -[FLAG]` - " +
                "View timing of specific" + " lesson type for a chosen module\n"
                +
                "6. `addmod [MODULE_CODE]` - Adds a module to your module list.\n" +
                "7. `addmod [MODULE_CODE] -[FLAG] [LESSON NUMBER]` - Adds a chosen lesson of a " +
                "specified module to your timetable! \n" +
                "8. `Remove a module (delmod [MODULE_CODE or IDX]` - Removes a Module you previously added by code " +
                "or index in module list.\n" +
                "9. `delmod [MODULE_CODE] -[FLAG] [LESSON NUMBER]` " +
                "- Removes a lesson of a specified module from your timetable. \n\n");
    }

    private void printUtility() {
        System.out.print("These are the Utility Commands:\n\n" +
                "1. `week` - Displays your schedule for the week.\n" +
                "2. `bye` - Exit the program\n" +
                "3. `help` - Get a summary of all the commands available on Apollo.\n" +
                "View help for a specific command by inputting help [COMMAND] \n"
        );

    }

    private void printNote(){
        System.out.println("NOTE: "+"showmod, addmod, delmod, listmod are commands with flags included in them. \n" +
                "Whatever in [THE SQUARE BRACKETS] are provided by you." +
                "For more information on the flags, please input \"help [COMMAND]\" exclusive of the square brackets." +
                " \n" + "For example, if you want to know more about the addmod command and its flags, input " +
                "\"help addmod\".\n");
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
    public void printWeek(TaskList taskList, Calendar calendar, LocalDate startWeek, LocalDate endWeek) {
        LocalDate curr = startWeek;
        int weekNumber = getWeekNumber(curr);
        System.out.println("Here's your week from " + startWeek + " to " + endWeek + ":");

        if (weekNumber == 0) {
            System.out.println("It is currently not AY22/23 Semester 2");
        } else if (weekNumber == -1) {
            System.out.println("Recess Week");
        } else if (weekNumber == 14) {
            System.out.println("Reading Week");
        } else if (weekNumber == 15 || weekNumber == 16) {
            System.out.println("Examination Week");
        } else {
            System.out.println("Week " + weekNumber);
        }


        for (int i = 0; i < 7; i++) {
            showSmallLine();
            System.out.println(determineDay(i) + "\n");

            // lessons
            ArrayList<CalendarModule> modulesOnDay = calendar.getModulesForDay(weekNumber, i);
            if (modulesOnDay.size() == 0) {
                System.out.println("There are no lessons on this day.");
            } else {
                printLessonsOnDay(modulesOnDay);
            }
            System.out.println();

            // tasks
            TaskList tasksOnDay = taskList.getTasksOnDate(curr);
            if (tasksOnDay.size() == 0) {
                System.out.println("There are no tasks on this day.");
            } else {
                printTasksOnDay(tasksOnDay);
            }

            // go to next day
            curr = curr.plusDays(1);
        }
    }

    private void printLessonsOnDay(ArrayList<CalendarModule> modulesOnDay) {
        System.out.println("Lessons:");
        for (CalendarModule module : modulesOnDay) {
            Timetable schedule = module.getSchedule();
            System.out.println(schedule.getStartTime() + "-" + schedule.getEndTime() + ": " +
                    module.getCode() + " " + schedule.getLessonType() + " (" + schedule.getClassNumber() + ")");
        }
    }

    private void printTasksOnDay(TaskList tasksOnDay) {
        System.out.println("Tasks:");
        int count = 0;
        for (Task task : tasksOnDay) {
            count++;
            System.out.println(count + ". " + task);
        }
    }

    public void printClashingDeadlineMessage(TaskList clashTasks, ArrayList<CalendarModule> clashLessons) {
        if (clashTasks.size() == 0 & clashLessons.size() == 0) {
            return;
        }
        System.out.println("Heads up, your deadline occurs on the same day as these!");
        showSmallLine();

        if (clashLessons.size() != 0) {
            System.out.println("Lessons:");
            for (CalendarModule module : clashLessons) {
                System.out.println( " - " + module.getCode() + " " + module.getSchedule());
            }
            System.out.println();
        }

        if (clashTasks.size() != 0) {
            System.out.println("Tasks:");
            for (Task task : clashTasks) {
                System.out.println(" - " + task);
            }
        }
        showSmallLine();

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
     * Prints out the timetable of a module added by the user in the module list.
     *
     * @param newModule Module that has just been added.
     */
    public void printModuleListWithLesson(Module newModule, ArrayList<Timetable> timetableList) {
        System.out.println("These are your classes for Module " + newModule.getCode() + ": \n");
        for (Timetable timetable : timetableList) {

            System.out.println(timetable.getLessonType() + " " + timetable.getClassNumber() + '\n' +
                    "   " + timetable.getDay() + " " + timetable.getStartTime() + " - " +
                    timetable.getEndTime() + " " + timetable.compressedWeeks(timetable));
        }

    }

    /**
     * Prints out message to inform user no lesson has been added to this module.
     *
     * @param moduleCode The code of the module checked.
     */
    public void printLessonsNotAdded(String moduleCode) {
        System.out.println("You have not added any lessons or classes to this module: " + moduleCode);
    }

    /**
     * Prints out message to inform user the specific lesson type has not been added to this module.
     *
     * @param moduleCode The code of the module checked.
     * @param lessonType The lesson type of this module to be checked.
     */
    public void printLessonTypeNotAdded(String moduleCode, LessonType lessonType) {
        System.out.println("You have not added " + lessonType + " lesson type for this module: " + moduleCode);

    }

    /**
     * Prints out message to inform user that this module is not added into the module list.
     *
     * @param moduleCode The code of the module checked.
     */
    public void printLessonNotInList(String moduleCode) {
        System.out.println("This module " + moduleCode + " is not in your Module List.");
    }

    /**
     * Prints out message to inform user that the module does not have this lesson type.
     */
    public void printLessonTypeNotInModule() {
        System.out.println("This lesson type does not exist in this module!");
    }

    /**
     * Prints out message to inform user that user has not added any classes.
     */
    public void printEmptyLessonTypeInModuleList() {
        System.out.println("You have not any classes to this module.");
    }

    /**
     * Prints out the timetable of a specific lesson type for a module in the module list.
     *
     * @param module The module to be checked.
     * @param lessonType The lesson type of the module to be checked.
     */
    public void printSpecificTimetable(Module module, LessonType lessonType, ArrayList<Timetable> copyList) {
        System.out.println("Here is your lesson of type: " + lessonType.toString() + " for "
                + module.getCode() + ":");

        for (Timetable timetable : copyList) {
            System.out.println("Class Number: " + timetable.getClassNumber());
            System.out.println("   " + timetable.getDay() + " " + timetable.getStartTime() + " - " +
                                timetable.getEndTime() + " " + timetable.compressedWeeks(timetable));
        }
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
     * For {@code showmod} command
     * Prints out message existing Module information
     *
     * @param newModule Module that needs to show information
     */
    public void printShowModuleMessage(Module newModule, ArrayList<LessonType> lessonTypes,
                                       ArrayList<Timetable> timetableList) {
        System.out.println(newModule.getCode() + '\n' +
                "Number of MC: " + newModule.getModuleCredits());
        printLessonTypeMessage(lessonTypes);
        System.out.println();
        for (Timetable timetable : timetableList) {

            System.out.println(timetable.getLessonType() + " " + timetable.getClassNumber() + '\n' +
                    "   " + timetable.getDay() + " " + timetable.getStartTime() + " - "
                    + timetable.getEndTime() + " " + timetable.compressedWeeks(timetable));
        }
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
        String dateString = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
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
        System.out.println("Please enter deadline as \"deadline [task] -by [date]\".");
    }

    /**
     * Prints error message if the event entered does not fit the format.
     */
    public void printInvalidEvent() {
        System.out.println("Please enter event as \"event [task] -from [date] -to [date]\".");
    }

    /**
     * Prints error message if the date entered does not fit the format.
     * For new Deadline, Event.
     */
    public void printInvalidDateTime() {
        System.out.println("Please enter [date]s in the format of dd-MM-yyyy-HH:mm.\n" +
                "eg. \"30-10-2023-23:59\" for Oct 30 2023, 11:59PM");
    }

    /**
     * Prints error message if date cannot exist in calendar
     */
    public void dateNotWithinCalender() {
        System.out.println("Please enter a valid date");
    }

    /**
     * Prints error message if the date entered does not fit the format.
     * For Date command.
     */
    public void printInvalidDate() {
        System.out.println("Please enter date in the format of dd-MM-yyyy.");
    }

    /**
     * Prints error message if the command entered is not understood by Apollo.
     */
    public void printInvalidCommand() {
        System.out.println("Sorry, but I don't know what that means :(");
    }
    public void printInvalidCommandForHelp(IllegalArgumentException exception) {
        System.out.println("Sorry, but the command \"" + exception.getMessage() + "\" does not exist in Apollo!\n" +
                "Input `help` to see all available commands.");
    }

    /**
     * Prints error message if there is corrupted data in the save file when initially reading from it.
     *
     * @param counter  Index of the line of the save file that is corrupted.
     * @param filePath The location at which the save file is stored.
     */
    public void printInvalidSaveFile(int counter, String filePath) {
        showLine();
        System.out.println("There is an error in save.txt at line " + counter + "\n" +
                "Task " + counter + " has been excluded. You can view the save file at:" + filePath);
        showLine();
    }

    /**
     * Prints error message if there are duplicate modules in the moduleData.txt file
     */
    public void printDuplicateModuleInTextFile(int counter){
        System.out.println("There is a duplicate module detected in the moduleData.txt at line "
                + (counter + 1) + ".\n" + "Ignoring duplicate modules");
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
     * Prints error message if the user does not specify the module code for module information.
     */
    public void printEmptyShowModCode() {
        System.out.println("Please enter a module code!");
    }

    /**
     * Prints error message if the start date of an event occurs after the end date.
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
        System.out.println("This module does not exist, or is not available this semester!\n" +
                "Please refer to official NUS module list for more information.");
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
     * @param moduleCode  Module code of the module whose lesson is being added.
     * @param lessonType  Type of lesson being added.
     * @param classNumber Class number of the lesson being added.
     */
    public void printClassAddedMessage(String moduleCode, LessonType lessonType, String classNumber) {
        System.out.println("Adding lesson type: " + lessonType + " for Module: " + moduleCode);
        System.out.println("Class Number: " + classNumber);
    }

    /**
     * Prints message when lesson is Invalid.
     */
    public void printInvalidLessonType() {
        System.out.println("This lesson type does not exist!");
    }

    /**
     * Prints message when lesson has already been added to the timetable.
     */
    public void printLessonExists() {
        System.out.println("This lesson type already exists for this lesson!");
    }

    /**
     * Prints message when lesson has not been added to the timetable.
     */
    public void printClassNotAdded() {
        System.out.println("This class has not been added to your timetable!");
    }

    /**
     * Prints message when lesson is deleted from the timetable.
     *
     * @param moduleCode   Module code of the module whose lesson is being deleted.
     * @param lessonType   Type of lesson being deleted.
     * @param lessonNumber Class number of the lesson being deleted.
     */
    public void printModuleLessonDeleteMessage(String moduleCode, LessonType lessonType, String lessonNumber) {
        System.out.println("Deleting lessons for module: " + moduleCode.toUpperCase());
        System.out.println("Lessons Deleted: " + lessonType + " - " + lessonNumber);
    }

    /**
     * Prints a message when a module does not have that particular lesson type.
     */
    public void printNoLessonType() {
        System.out.println("This module does not have this lesson type");
    }

    /**
     * Prints message of lesson schedule for a particular lesson type for a module
     *
     * @param module The module whose lesson schedule is being printed.
     * @param lessonType The lesson type whose schedule is being printed.
     * @param copyList The list of lessons of that lesson type for that module.
     */
    public void printModuleLessonTimetable(Module module, LessonType lessonType, ArrayList<Timetable> copyList) {
        System.out.println("Here are all available lessons of type: " + lessonType.toString() + " for "
                + module.getCode() + ":");

        for (Timetable timetable : copyList) {
            System.out.println("Class Number: " + timetable.getClassNumber());
            System.out.println("   " + timetable.getDay() + " " + timetable.getStartTime() + " - " +
                    timetable.getEndTime() + " " + timetable.compressedWeeks(timetable));
        }
    }

    /**
     * Prints message when the user tries to add a lesson that clashes with another lesson in the timetable.
     */
    public void printClashingLesson() {
        System.out.println("This lesson clashes with another lesson in your timetable!");
    }

    public void printClashingEventMessage() {
        System.out.println("This event clashes with another event in your timetable!");
    }

    public void printClashingEventModuleMessage() {
        System.out.println("This event clashes with a lesson in your timetable!");
    }

    /**
     * Prints a message when user tries to mark an already done task as done again.
     */
    public void printTaskHasBeenMarkedPreviously() {
        System.out.println("You have already marked this task as done previously.");
    }

    /**
     * Prints a message when user tries to mark an already incomplete task as not done again.
     */
    public void printTaskHasBeenUnmarkedPreviously(){
        System.out.println("This task was never marked as done!");
    }

    public void deadlineSuggestion(){
        System.out.println("This todo seems to suggest that this is a deadline type task.\n" +"You could consider " +
                "using the deadline command instead.\n");
    }


    /**
     * Prints a help message for date command
     */
    public void printDateHelpMessage() {
        System.out.println("Shows all tasks in Apollo that occur on the specified date.\n" +
                "\n" +
                "Format: date DATE\n" +
                "\n" +
                "Note: `DATE` should be entered in the format `dd-MM-yyyy`.");
    }
    /**
     * Prints a help message for find command
     */
    public void printFindHelpMessage() {
        System.out.println("Shows all tasks in Apollo that contain the specified keyword.\n" +
                "\n" +
                "Format: find KEYWORD");
    }
    /**
     * Prints a help message for delete command
     */
    public void printDeleteHelpMessage() {
        System.out.println("Deletes the specified task from Apollo.\n" +
                "\n" +
                "Format: `delete IDX`\n" +
                "\n" +
                "Note: `IDX` can be obtained by using `list` to find the task's index.\n");
    }
    /**
     * Prints a help message for unmark command
     */
    public void printUnmarkHelpMessage() {
        System.out.println("Marks the specified task as not completed. \n" +
                "\n" +
                "Format: unmark IDX\n" +
                "\n" +
                "Note: `IDX` can be obtained by using `list` to find the task's index.");
    }
    /**
     * Prints a help message for mark command
     */
    public void printMarkHelpMessage() {
        System.out.println("Marks the specified task as completed.\n" +
                "\n" +
                "Format: mark IDX\n" +
                "\n" +
                "Note: `IDX` can be obtained by using `list` to find the task's index.");
    }
    /**
     * Prints a help message for event command
     */
    public void printEventHelpMessage() {
        System.out.println("Adds a task with a start and end date to Apollo.\n" +
                "If there is an event in the tasklist that is clashing with any event added previously " +
                "a warning message will be printed. \n" +
                "However, you will still be able to add it.\n\n" +
                "Format: event TASK -from DATE -to DATE\n" +
                "Note: DATE must be entered in the format dd-MM-yyyy-hh:mm.");
    }
    /**
     * Prints a help message for list command
     */
    public void printListHelpCommand() {
        System.out.println("Shows a numbered list of all tasks (Todos, Events, Deadlines) in Apollo. " +
                "`list` automatically sorts the tasks by type, \n" +
                "then date within each type." + "Format: list");
    }
    /**
     * Prints a help message for todo command
     */
    public void printTodoHelpMessage() {
        System.out.println("Adds a normal task to Apollo.\nFormat: todo TASK");
    }
    /**
     * Prints a help message for deadline command
     */
    public void printDeadlineHelpMessage() {
        System.out.println("Adds a task with a due date to Apollo \n" + "Format: deadline TASK -by DATE\n" +
                "Note: DATE must be entered in the format dd-MM-yyyy-HH:mm.\n"+
                "If deadline clashes with any event or lesson type you will be alerted through a warning message. \n" +
                "However, you will still be able to add it into the tasklist.");
    }

    public void printListModuleWithoutFlagsHelpMessage(){
        System.out.println("Shows the list of modules you are taking for this semester, " +
                "alongisde total Modular Credits (MC).\n"+
                "The list will be automatically sorted in alphabetical order according to EduRec standards.\n" +
                "Format: listmod\n");
    }

    public void printListModuleWithCodeHelpMessage(){
        System.out.println("To see all class timings for all lesson types that you have added for a module in your " +
                "list, \n" + "you can append the module code to the listmod command. \n" +
                "Format: listmod MODULE_CODE\n");
    }

    public void printListModuleWithFlagHelpMessage(){
        System.out.println("To see all class timings for a specific lesson type that you have added " +
                "for a module in your list, \n" +
                "you can append the module code and the lesson type flag to the listmod command. \n" +
                "Format: listmod MODULE_CODE -FLAG\n" + "Example: listmod CS1010 -st\n\n" +
                "Note: You must have added a module and at least one lesson into your list. \n");
    }

    public void printListModuleHelpMessage() {

        printListModuleWithoutFlagsHelpMessage();
        printListModuleWithCodeHelpMessage();
        printListModuleWithFlagHelpMessage();
        printModuleFlagOptions();

    }
    public void printExitHelpMessage(){
        System.out.println("Exit Apollo.\n" +
                "Format: bye");
    }
    public void printWeekHelpMessage(){
        System.out.println("Shows your timetable for the current week.\n" +
                "Format: week");
    }

    /**
     * Prints out a list of all available lesson types and their flags.
     */
    public void printModuleFlagOptions() {
        System.out.println("There are -FLAGS for the various lessons options per module:\n" +
                "-lec\t\t\t" + "LECTURE\n" +
                "-plec\t\t\t" + "PACKAGED LECTURE\n" +
                "-st \t\t\t" + "SECTIONAL TEACHING\n" +
                "-dlec\t\t\t" + "DESIGN LECTURE\n" +
                "-tut\t\t\t" + "TUTORIAL\n" +
                "-ptut\t\t\t" + "PACKAGED TUTORIAL\n" +
                "-rcit\t\t\t" + "RECITATION\n" +
                "-lab\t\t\t" + "LABORATORY\n" +
                "-ws \t\t\t" + "WORKSHOP\n" +
                "-smc\t\t\t" + "SEMINAR STYLE MODULE CLASS\n" +
                "-mp \t\t\t" + "MINI PROJECT\n"
                + "-tt2\t\t\t" + "TUTORIAL TYPE 2");
    }

    public void printDelmodOptions() {
        System.out.println("Removes a module from Apollo. Can be done using either IDX or MODULE_CODE.\n"+
               "Deletion by IDX (Note: IDX can be obtained by using listmod to find the module's index):\n"+
                "Format: delmod IDX\n\n" + "Delete by MODULE_CODE:\n"+"Format: delmod MODULE_CODE\n" );
    }

    public void printDeleteLessonMessage(){
        System.out.println("You can delete a module lesson from your timetable.\n" +
                "Format: delmod MODULE_CODE -FLAG LESSON_NUMBER\n" +
                "Example: delmod CS2113T -lec 1\n"+
                "NOTE: LESSON_NUMBER must strictly follow that of NUSMods. If it is Lecture 01 and Lecture 1, \n" +
                "the corresponding flag inputs are -lec 01 and -lec 1 respectively.\n");
    }

    public void printDeleteModHelpMessage(){
        printDelmodOptions();
        printDeleteLessonMessage();
        printModuleFlagOptions();

    }

    public void printAddModuleHelp(){
        System.out.println("Add a module into your module list.\n" +
                "Format: addmod MODULE_CODE\n");

    }

    public void printAddModuleLessonHelp(){
        System.out.println("You can also add module lessons into your timetable.\n" +
                "Format: addmod MODULE_CODE -FLAG LESSON_NUMBER\n" +
                "Example: addmod CS2113 -lec 1\n"+
                "NOTE: LESSON_NUMBER must strictly follow that of NUSMods. If it is Lecture 01 and Lecture 1, \n" +
                "the corresponding flag inputs are -lec 01 and -lec 1 respectively.\n");

    }

    public void printAddModHelpMessage(){
        printAddModuleHelp();
        printAddModuleLessonHelp();
        printModuleFlagOptions();
    }

    public void printShowModuleInfoHelpMessage(){
        System.out.println("Shows the information of a module, including Modular Credits, lesson types, lesson numbers "
                + "and times.\n" +
                "Format: showmod MODULE_CODE\n");

    }

    public void printShowLessonInfoHelpMessage(){
        System.out.println("If you would like to view timing information on a specific lesson type of a module, " +
                "you can use flags.\n"
                +  "Format: showmod MODULE_CODE -FLAG\n" + "Example: showmod CS1010 -st\n\n" +
                "NOTE: Different modules have different lesson types.\n" +
                "It is recomended to run `showmod MODULE_CODE` to see the lesson types available for that module.\n");
    }

    public void printShowModuleHelpMessage(){
        printShowModuleInfoHelpMessage();
        printShowLessonInfoHelpMessage();
        printModuleFlagOptions();
    }

}
