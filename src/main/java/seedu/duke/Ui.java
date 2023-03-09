package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class Ui {
    public void printWelcomeMessage() {
        System.out.println("Hello, I am Duke and I will help you remember the tasks you need to do!");
        System.out.println("For now, you can do the following:");
        System.out.println("Add a task using: add <description>");
        System.out.println("List all tasks using: list");
        System.out.println("Mark a task as done using: mark <index>");
        System.out.println("Mark a task as undone using: unmark <index>");
        System.out.println("Delete a task using: delete <index>");
        System.out.println("Exit the program using: bye");
    }

    public void printAddTaskNotification(Task task) {
        System.out.println("Got it, I have added the following task:");
        System.out.println(task.toString());
    }

    public void printMarkTaskNotification(Task task) {
        System.out.println("Got it, I have marked the following task as done:");
        System.out.println(task.toString());
    }

    public void printUnmarkTaskNotification(Task task) {
        System.out.println("Got it, I have marked the following task as undone:");
        System.out.println(task.toString());
    }

    public void printDeleteTaskNotification(TaskList taskList, int index) {
        System.out.println("Noted. I've removed this task:");
        Task task = taskList.getTask(index);
        System.out.println(task.toString());
    }

    public void listTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            printEmptyList();
        } else {
            System.out.println(taskList.toString());
        }
    }

    public void printGoodbyeMessage() {
        System.out.print("See you again, bye!");
    }

    public void printErrorMessage() {
        System.out.println("Error encountered! Please type in a valid command!");
    }

    public void printEmptyList(){
        System.out.println("There are no tasks in the list.");
    }
}
