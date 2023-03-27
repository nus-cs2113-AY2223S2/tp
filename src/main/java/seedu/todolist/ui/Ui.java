package seedu.todolist.ui;

import seedu.todolist.constants.Messages;

import java.util.Scanner;

public class Ui {
    private final Scanner input = new Scanner(System.in);

    public String getUserInput() {
        return input.nextLine();
    }

    public void close() {
        input.close();
    }

    /**
     * Prints out the given strings with a newline separating them.
     *
     * @param strings The strings to print out.
     */
    private void println(String... strings) {
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
        println(Messages.START.getMessage());
    }

    public void printNewSaveMessage() {
        println(Messages.NEW_SAVE.getMessage());
    }

    public void printLoadSaveMessage(int taskListSize) {
        println(Messages.LOAD_SAVE.getMessage() + generateTaskCountString(taskListSize));
    }

    public void printGoodbyeMessage() {
        println(Messages.EXIT.getMessage());
    }

    public void printAddTaskMessage(String taskString) {
        println(Messages.ADD_TASK.getMessage(), taskString);
    }

    public void printMarkTaskMessage(String taskString) {
        println(Messages.MARK_TASK.getMessage(), taskString);
    }

    public void printUnmarkTaskMessage(String taskString) {
        println(Messages.UNMARK_TASK.getMessage(), taskString);
    }

    public void printDeleteTaskMessage(String taskString) {
        println(Messages.DELETE_TASK.getMessage(), taskString);
    }

    public void printEditTaskMessage(String taskString) {
        println(Messages.EDIT_TASK.getMessage(), taskString);
    }

    public void printSetEmailMessage(String taskString) {
        println(Messages.SET_EMAIL.getMessage(), taskString);
    }

    public void printGetTaskEmailMessage(String email) {
        println(Messages.GET_EMAIL.getMessage(), email);
    }
        
    public void printCheckRepeatingTaskMessage() {
        println(Messages.CHECK_REPEATING.getMessage());
    }

    public void printTagInfo(int tagCount, String tagString) {
        if (tagCount == 0) {
            println(Messages.TAG_EMPTY.getMessage());
        } else {
            println(Messages.TAG_INFO.getMessage(), tagString);
        }
    }

    public void printDeletedTagsMessage(String tagString) {
        println(Messages.TAG_DELETE.getMessage(), tagString);
    }

    public void printTaskList(int taskListSize, String taskListString) {
        if (taskListSize == 0) {
            println(Messages.LIST_EMPTY.getMessage());
        } else {
            println(Messages.LIST_TASKS.getMessage() + generateTaskCountString(taskListSize),
                    taskListString);
        }
    }

    public void printError(Exception e) {
        println(e.getMessage());
    }

    //@@author jeromeongithub
    public void printProgressBar(int completedTasksThisWeek, int tasksThisWeek,
                                 int totalSections, String taskListString) {
        if (tasksThisWeek == 0) {
            println("You have no tasks due this week!");
            return;
        }

        double progress = (double) completedTasksThisWeek / tasksThisWeek;
        int completedSections = (int) (progress * totalSections);
        int incompleteSections = totalSections - completedSections;

        println("You have completed " + 100 * progress + "% of the "
                + generateTaskCountString(tasksThisWeek) + " due this week!",
                "Progress: |" + "=".repeat(completedSections) + "-".repeat(incompleteSections) + "|",
                taskListString);
    }
}
