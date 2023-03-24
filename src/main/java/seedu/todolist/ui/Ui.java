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

    private void printWithNewlineSeparator(String... strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public void printWelcomeMessage() {
        printWithNewlineSeparator(Messages.START.MESSAGE);
    }

    public void printNewSaveMessage() {
        printWithNewlineSeparator(Messages.NEW_SAVE.MESSAGE);
    }

    public void printLoadSaveMessage(int taskListSize) {
        printWithNewlineSeparator(Messages.LOAD_SAVE.MESSAGE + " " + generateTaskCountString(taskListSize));
    }

    public void printGoodbyeMessage() {
        printWithNewlineSeparator(Messages.EXIT.MESSAGE);
    }

    public void printAddTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.ADD_TASK.MESSAGE, taskString);
    }

    public void printMarkTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.MARK_TASK.MESSAGE, taskString);
    }

    public void printUnmarkTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.UNMARK_TASK.MESSAGE, taskString);
    }

    public void printDeleteTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.DELETE_TASK.MESSAGE, taskString);
    }

    public void printEditTaskMessage(String taskString) {
        printWithNewlineSeparator(Messages.EDIT_TASK.MESSAGE, taskString);
    }

    public void printCheckRepeatingTaskMessage() {
        printWithNewlineSeparator(Messages.CHECK_REPEATING.MESSAGE);
    }

    private String generateTaskCountString(int taskListSize) {
        return taskListSize + " task" + (taskListSize == 1 ? "" : "s") + " found.";
    }

    public void printTaskList(int taskListSize, String taskListString) {
        if (taskListSize == 0) {
            printWithNewlineSeparator(Messages.EMPTY_LIST.MESSAGE);
        } else {
            printWithNewlineSeparator(Messages.LIST_TASKS.MESSAGE + " " + generateTaskCountString(taskListSize),
                    taskListString);
        }
    }

    public void printError(Exception e) {
        printWithNewlineSeparator(e.getMessage());
    }
}
