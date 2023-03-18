package seedu.duke.ui;

import seedu.duke.task.TaskList;

public class Ui {
    public void printWelcomeMessage() {
        System.out.println("Hello, I am Duke and I will help you remember the tasks you need to do!");
        System.out.println("For now, you can do the following:");
        System.out.println("Add a task using: add <description> -d <deadline>");
        System.out.println("List all tasks using: list");
        System.out.println("Delete a task using: delete <index>");
        System.out.println("Mark a task as done using: mark <index>");
        System.out.println("Mark a task as undone using: unmark <index>");
        System.out.println("Edit a task deadline using: edit <index> -d <deadline>");
        System.out.println("Exit the program using: exit");
        System.out.println();
    }

    public void printGoodbyeMessage() {
        System.out.print("See you again, bye!");
    }

    public void printAddTaskNotification(String taskString) {
        System.out.println("Got it, I have added the following task:");
        System.out.println(taskString);
    }

    public void printMarkTaskNotification(String taskString) {
        System.out.println("Got it, I have marked the following task as done:");
        System.out.println(taskString);
    }

    public void printUnmarkTaskNotification(String taskString) {
        System.out.println("Got it, I have marked the following task as undone:");
        System.out.println(taskString);
    }

    public void printEditDeadlineNotification(String taskString) {
        System.out.println("Okay, I have edited the deadline for the following task:");
        System.out.println(taskString);
    }

    public void printDeleteTaskNotification(String taskString) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskString);
    }

    public void listTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            taskList.sortByDeadline();
            System.out.println(taskList);
        }
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
