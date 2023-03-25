package seedu.todolist.ui;

import seedu.todolist.constants.Messages;

import java.util.Scanner;

public class Ui {
    private final Scanner INPUT = new Scanner(System.in);

    public String getUserInput() {
        return INPUT.nextLine();
    }

    public void close() {
        INPUT.close();
    }

    /**
     * Prints out the given strings with a newline separating them.
     *
     * @param strings The strings to print out.
     */
    private void printWithNewlineSeparator(String... strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }

    /**
     * Generates a grammar-appropriate string based on the number of tasks in the task list,
     * such as "0 tasks", "1 task", "2 tasks".
     *
     * @param taskListSize The size of the task list.
     * @return The task count string.
     */
    private String generateTaskCountString(int taskListSize) {
        return taskListSize + " task" + (taskListSize == 1 ? "" : "s");
    }

    public void printWelcomeMessage() {
        printWithNewlineSeparator(Messages.START.getMessage());
    }

    public void printNewSaveMessage() {
        printWithNewlineSeparator(Messages.NEW_SAVE.getMessage());
    }

    public void printLoadSaveMessage(int taskListSize) {
        printWithNewlineSeparator(Messages.LOAD_SAVE.getMessage() + generateTaskCountString(taskListSize));
    }

    public void printGoodbyeMessage() {
        printWithNewlineSeparator(Messages.EXIT.getMessage());
    }

    public void printAddTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.ADD_TASK.getMessage(), taskString);
    }

    public void printMarkTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.MARK_TASK.getMessage(), taskString);
    }

    public void printUnmarkTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.UNMARK_TASK.getMessage(), taskString);
    }

    public void printDeleteTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.DELETE_TASK.getMessage(), taskString);
    }

    public void printEditTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.EDIT_TASK.getMessage(), taskString);
    }

    public void printSetEmailMessage(String taskString) {
        printWithNewlineSeparator(Messages.SET_EMAIL.getMessage(), taskString);
    }

    public void printGetTaskEmailMessage(String email) {
        printWithNewlineSeparator(Messages.GET_EMAIL.getMessage(), email);
    }
        
    public void printCheckRepeatingTaskMessage() {
        printWithNewlineSeparator(Messages.CHECK_REPEATING.getMessage());
    }

    public void printTagInfo(int tagCount, String tagString) {
        if (tagCount == 0) {
            printWithNewlineSeparator(Messages.TAG_EMPTY.getMessage());
        } else {
            printWithNewlineSeparator(Messages.TAG_INFO.getMessage(), tagString);
        }
    }

    public void printDeletedTagsMessage(String tagString) {
        printWithNewlineSeparator(Messages.TAG_DELETE.getMessage(), tagString);
    }

    public void printTaskList(int taskListSize, String taskListString) {
        if (taskListSize == 0) {
            printWithNewlineSeparator(Messages.LIST_EMPTY.getMessage());
        } else {
            printWithNewlineSeparator(Messages.LIST_TASKS.getMessage() + generateTaskCountString(taskListSize),
                    taskListString);
        }
    }

    public void printError(Exception e) {
        printWithNewlineSeparator(e.getMessage());
    }

    //@@author jeromeongithub
    public void printProgressBar(int tasksThisWeek, int completedTasksThisWeek, int totalSections) {
        double progress = 100.0 * completedTasksThisWeek / tasksThisWeek;
        int completedSections = (int) (progress * totalSections);
        int incompleteSections = totalSections - completedSections;

        printWithNewlineSeparator("You have completed " + progress + "% of the tasks that are due this week!",
                "Progress: |" + "=".repeat(completedSections) + "-".repeat(incompleteSections) + "|");
    }
}
