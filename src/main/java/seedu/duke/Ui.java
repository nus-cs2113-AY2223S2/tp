package seedu.duke;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello, I am Duke and I will help you remember the tasks you need to do!");
        System.out.println("For now, you can do the following:");
        System.out.println("Add a task using: add <description>");
        System.out.println("List all tasks using: list");
        System.out.println("Mark a task as done using: mark <index>");
        System.out.println("Mark a task as undone using: unmark <index>");
        System.out.println("Exit the program using: bye");
    }

    public static void printAddTaskNotification(String description) {
        System.out.println("Got it, I have added the following task:");
        System.out.println(description);
    }

    public static void printMarkTaskNotification(String description) {
        System.out.println("Got it, I have marked the following task as done:");
        System.out.println(description);
    }

    public static void printUnmarkTaskNotification(String description) {
        System.out.println("Got it, I have marked the following task as undone:");
        System.out.println(description);
    }

    public static void listTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.print("description: " + tasks.get(i).getDescription() + ", ");
            System.out.print("status: ");
            if (tasks.get(i).getIsDone()) {
                System.out.println("done");
            } else {
                System.out.println("undone");
            }
        }
    }

    public static void printGoodbyeMessage() {
        System.out.print("See you again, bye!");
    }

    public static void printErrorMessage() {
        System.out.println("Error encountered! Please type in a valid command!");
    }
}
