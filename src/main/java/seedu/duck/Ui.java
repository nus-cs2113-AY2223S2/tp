package seedu.duck;

import seedu.duck.task.Deadline;
import seedu.duck.task.Event;
import seedu.duck.task.SchoolClass;
import seedu.duck.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

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
     * @param matchCount      The number of tasks in the list that contain the keywords
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
     * @param matchCount      The number of tasks in the list that contain the keywords
     */
    static void printMatchingList(ArrayList<Task> matchingResults, int matchCount) {
        borderLine();
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < matchCount; i++) {
            System.out.println("\t " + (i + 1) + "." + matchingResults.get(i));
        }
        borderLine();
    }

    /**
     * Display upcoming deadline
     *
     * @param tasks tasks store in the file
     */
    static void displayUpcomingDeadline(ArrayList<Task> tasks) {
        System.out.println("\t Here are the upcoming deadline:  ");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmm");
                String deadline = ((Deadline) tasks.get(i)).getDeadline();
                Date d = null;
                Date n = new Date();
                try {
                    d = format.parse(deadline);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = d.getTime() - n.getTime();
                String di = getTimeDiff(diff);
                String description = tasks.get(i).getDescription().replace("deadline ", "");
                System.out.println("\t " + (count + 1) + "." + description+" ("+di+"before the deadline)");
                count++;
            }
        }
        borderLine();
    }

    /**
     * Prints the list of tasks in x days in the future
     *
     * @param tasks the array list of all the tasks
     * @param days the required the number of days x from now onwards
     */
    static void printUpcomingTasks(ArrayList<Task> tasks, String days) {
        borderLine();
        System.out.println("\t Here are your tasks in " + days + " days:");
        int count = 0;
        Date d = null;
        Date n = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmm");
        for (int i = 0; i < tasks.size(); i ++) {
            String timeUntilTask = null;
            if (tasks.get(i) instanceof Deadline) {
                timeUntilTask = ((Deadline) tasks.get(i)).getDeadline();
            } else if (tasks.get(i) instanceof Event) {
                timeUntilTask = ((Event) tasks.get(i)).getStart();
            } else if (tasks.get(i) instanceof SchoolClass) {
                timeUntilTask = ((SchoolClass) tasks.get(i)).getStart();
            }
            if (timeUntilTask != null) {
                try {
                    d = format.parse(timeUntilTask);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = d.getTime() - n.getTime();
                String di = getTimeDiff(diff);
                String[] diffSplit = di.split(" ");
                if (diffSplit.length >= 2 && ((diffSplit[1].contains("day") && Integer.parseInt(diffSplit[0])
                        <= Integer.parseInt(days)) || diffSplit[1].contains("hour")
                        || diffSplit[1].contains("minute"))) {
                    count++;
                    System.out.println("\t " + count + "." + tasks.get(i).toString());
                }
            }
        }
        borderLine();
    }

    /**
     * Function help for calculating time difference
     * @param timeDifferenceMilliseconds time difference between now and deadline
     * @return time difference in structured format
     */
    static String getTimeDiff(long timeDifferenceMilliseconds) {
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000) % 60;
        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000) % 60;
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24) % 24;
        long diffMonths = (long) ((timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666)) % 30.41666666);
        long diffYears = timeDifferenceMilliseconds / ((long) 60 * 60 * 1000 * 24 * 365) % 365;
        String result = "";
        if (diffYears != 0) {
            result += diffYears;
            result += " year";
            if (diffYears != 1) {
                result += "s";
            }
            result += " ";
        }
        if (diffMonths != 0) {
            result += diffMonths;
            result += " month";
            if (diffMonths != 1) {
                result += "s";
            }
            result += " ";
        }
        if (diffDays != 0) {
            result += diffDays;
            result += " day";
            if (diffDays != 1) {
                result += "s";
            }
            result += " ";
        }
        if (diffHours != 0) {
            result += diffHours;
            result += " hour";
            if (diffHours != 1) {
                result += "s";
            }
            result += " ";
        }
        if (diffMinutes != 0) {
            result += diffMinutes;
            result += " minute";
            if (diffMinutes != 1) {
                result += "s";
            }
            result += " ";
        }
        return result;
    }

    /**
     * Prints the border for opening or closing messages
     */
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

    static boolean doubleCheck() {
        System.out.println("\t THIS IS AN IRREVERSIBLE PROCESS. ARE YOU SURE? Y/N");
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        return Objects.equals(line, "Y");
    }

    static void help() {
        borderLine();
        System.out.println("\t （`･v･´ ）: Here are the commands you can give me:");
        System.out.println("\t - list: I'll list out all the tasks you have recorded.");
        System.out.println("\t - list <number_of_days>: I'll list out all the tasks in that number of days.");
        System.out.println("\t - clear: The list will be cleared. This in an irreversible process.");
        System.out.println("\t - mark <task_number>: I'll mark that task as done.");
        System.out.println("\t - unmark <task_number>: I'll mark that task as undone.");
        System.out.println("\t - delete <task_number>: I'll delete that task from your list.");
        System.out.println("\t - purge: I'll delete all expired tasks from your list after a confirmation.");
        System.out.println("\t - find <keyword>: I'll find the tasks in your list that contain the keyword.");
        System.out.println("\t - priority <task_number> <1/2/3>: I'll set the priority of a given task as");
        System.out.println("\t                                   1:Low, 2:Medium and 3:High.");
        System.out.println("\t - bye: I will shut down my program.\n");
        System.out.println("\t Here are the following ways to input tasks:");
        System.out.println("\t Deadlines: <description> /by <yyyy-MM-dd HHmm>");
        System.out.println("\t            (eg. Eat bread /by 2023-03-15 2015)");
        System.out.println("\t Events   : <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        System.out.println("\t            (eg. Meeting /from 2023-03-15 2015 /to 2023-03-15 2215)");
        System.out.println("\t Classes  : <description> /class <class_name> /from <yyyy-MM-dd HHmm> " +
                "/to <yyyy-MM-dd HHmm>");
        System.out.println("\t            (eg. Bring laptop /class CS2113 /from 2023-03-15 1100 /to 2023-03-15 1200)");
        System.out.println("\t Todo     : <description>");
        System.out.println("\t            (eg. Water the plants) \n");
        System.out.println("\t （`･v･´ ）: How else may I assist you today, human?");
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

    static void expiredErrorMessage() {
        borderLine();
        System.out.println("\t Quack! I know humans wish to undo their past mistakes, " +
                "but the start date has already passed!");
        System.out.println("\t Please try again!");
        borderLine();
    }

    static void startAfterEndErrorMessage() {
        borderLine();
        System.out.println("\t Quack! Somehow this human has time travelled, " +
                "and the start date seems to be after the end date! ");
        System.out.println("\t Please try again!");
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

    static void schoolClassErrorMessage() {
        borderLine();
        System.out.println("\t Error. Please enter a valid class name, description, start time and end time");
        borderLine();
    }

    static void invalidDateTimeMessage() {
        borderLine();
        System.out.println("\t Please check the inputted format human!\n" +
                "\t There are only 24 hours in a day in Duck World, and 12 months a year...\n");
        System.out.println("\t Please try again!");
        borderLine();
    }

    /**
     * Prints the startup message, includes instructions on available commands
     */
    static void greetingMessage() {
        borderLine();
        System.out.println("\t （`･v･´ ）: Nice to meet you human. As you can see,  I'm a Duck.");
        System.out.println("\t （´˘v˘´ ）: As a Duck, I can only understand simple commands. " +
                "Human speech is so confusing!");
        System.out.println("\t （´˘v˘´ ）: That being said, I am a smart Duck. " +
                "If you wish to know what I understand, just enter 'help'.");
        System.out.println("\t （`･v･´ ）: How may I assist you today, human?");
    }

    /**
     * Prints the exiting message when closing the program
     */
    static void exitMessage() {
        borderLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        borderLine();
    }

}
